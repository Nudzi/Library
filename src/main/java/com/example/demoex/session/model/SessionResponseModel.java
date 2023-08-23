package com.example.demoex.session.model;

import com.example.demoex.model.Members;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;

public class SessionResponseModel {
    private String sessionId;
    private Boolean isAnonymous;
    private Members member;
    private Cookie cookie;
    private LocalDateTime activeSince = LocalDateTime.now();

    public LocalDateTime getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(LocalDateTime activeSince) {
        this.activeSince = activeSince;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Members getMember() {
        return member;
    }

    public void setMember(Members member) {
        this.member = member;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}
