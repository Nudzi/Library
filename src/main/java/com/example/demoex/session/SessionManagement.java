package com.example.demoex.session;

import com.example.demoex.model.Members;
import com.example.demoex.session.model.DenyListModel;
import com.example.demoex.session.model.SessionResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionManagement extends HttpServlet {

    private static final String cookieName = "Library-Session-ID";
    private static final String cookieValue = "1234";
    private static final String domain = "library-name.com";
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final List<HttpSession> sessionList = new ArrayList<>();
    private final List<HttpSession> activeSessionList = new ArrayList<>();
    private final List<HttpSession> inactiveSessionList = new ArrayList<>();

    public SessionManagement(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public Optional<String> isCookieInactive(String cookieName) {
        return Arrays.stream(request.getCookies())
                .filter(c -> cookieName.equals(c.getName()) && c.getMaxAge() == 0)
                .map(Cookie::getValue)
                .findAny();
    }

    public Optional<String> readCookie(String cookieName) {
        return Arrays.stream(request.getCookies())
                .filter(c -> cookieName.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public SessionResponseModel checkSession(Members members, String host, String memberAgent, Cookie cookie) {
        if (memberAgent.isEmpty()) {
            memberAgent = request.getHeader("Member-Agent");
        }

        HttpSession session = request.getSession(false);
        SessionResponseModel sessionResponse = new SessionResponseModel();
        boolean isAnonymous = false;

        if (session == null) {
            session = request.getSession(true);
            isAnonymous = true;
        }

        if (readCookie(cookie.getName()).isEmpty() || !host.equals(domain)) {
            isAnonymous = true;
        }

        // get anonymous session
        String sessionId = session.getId();
        Long requestMemberId = Long.parseLong(sessionId);

        if (members != null) {
            requestMemberId = members.getId();
        }

        if (Boolean.TRUE.equals(isAnonymous)) {
            session.setAttribute("memberId", requestMemberId);
            session.setAttribute("sessionId", sessionId);
            session.setAttribute("memberAgent", memberAgent);
        }

        if (cookie.getValue().equals("null")) {
            cookie = createCookie();
        }
        response.addCookie(createCookie());
        sessionResponse.setAnonymous(isAnonymous);
        sessionResponse.setSessionId(sessionId);
        sessionResponse.setMember(members);
        sessionResponse.setCookie(cookie);
        session.setAttribute("sessionResponse", sessionResponse);
        addSessionToTheList(session);
        return sessionResponse;
    }

    private void addSessionToTheList(HttpSession postSession) {
        Optional<HttpSession> existingSessions = sessionList
                .stream().parallel()
                .filter(session -> session.getId().equals(postSession.getId())).findAny();
        if (existingSessions.isEmpty()) {
            sessionList.add(postSession);
        }
    }

    public Cookie createCookie() {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(1024 * 1024);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setDomain(domain);
        return cookie;
    }

    public Cookie deleteCookie() {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setDomain(domain);
        return cookie;
    }

    public HttpSession checkSessionAndSessionIdInCookie() {
        HttpSession session = request.getSession();

        if (session == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request.");
        } else if (session.getAttribute("sessionId").toString().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request.");
        } else return session;
    }

    public void addMemberToDenyList(String email, String password) {
        HttpSession session = getSession();

        session.setAttribute("denyListModel", new DenyListModel(email, password, getClientIp()));
    }

    public String getClientIp() {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public List<HttpSession> getAllSessions() {
        return sessionList;
    }

    public List<HttpSession> getAllActiveSessions() {
        filterSessionsByActivity();
        return activeSessionList;
    }

    public List<HttpSession> getAllInactiveSessions() {
        filterSessionsByActivity();
        return inactiveSessionList;
    }

    public void filterSessionsByActivity() {
        for (HttpSession session : sessionList) {
            SessionResponseModel sessionResponseModel = (SessionResponseModel) session.getAttribute("sessionResponse");
            if (sessionResponseModel.getCookie().getMaxAge() == 0) {
                inactiveSessionList.add(session);
            } else {
                activeSessionList.add(session);
            }
        }
    }

    public Optional<HttpSession> getSessionBySessionId(String sessionId) {
        return sessionList
                .stream().parallel()
                .filter(session -> session.getId().equals(sessionId)).findAny();
    }

    public HttpSession getSessionByMemberId(Long memberId) {
        for (HttpSession session : sessionList) {
            SessionResponseModel sessionResponseModel = (SessionResponseModel) session.getAttribute("sessionResponse");
            if (sessionResponseModel.getMember().getId().equals(memberId)) {
                return session;
            }
        }
        return null;
    }
}
