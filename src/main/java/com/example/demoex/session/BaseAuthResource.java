package com.example.demoex.session;

import com.example.demoex.model.MemberRole;
import com.example.demoex.model.Members;
import com.example.demoex.model.enums.MemberRoleTypes;
import com.example.demoex.service.MemberService;
import com.example.demoex.service.memberRole.MemberRoleService;
import com.example.demoex.session.model.DenyListModel;
import com.example.demoex.session.model.UserCacheModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class BaseAuthResource extends SessionResource {
    private static final String AUTH_HEADER_PREFIX = "Basic ";
    private final MemberService memberService;
    private final MemberRoleService memberRoleService;

    public BaseAuthResource(MemberService memberService, HttpServletRequest request,
                            HttpServletResponse response, MemberRoleService memberRoleService) {
        super(request, response);
        this.memberService = memberService;
        this.memberRoleService = memberRoleService;
    }

    protected Members verifyEmailAndPassword(String requestEmail, String requestPassword) {
        Members memberByEmail = memberService.getMemberByEmail(requestEmail);
        if (memberByEmail == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect email.");
        } else return memberByEmail;

//        TO DO: implement password *cryption
//        Members user = memberService.getUserByEmailAndPassword(requestEmail, requestPassword);

//        if (user == null) {
        // failed attempt
//            logNewUserAttempt(requestEmail, requestPassword);
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password.");
//        } else return user;
    }


    protected boolean verifyEmailIsNotInADenyList() {
        HttpSession session = getSession();
        DenyListModel denyListModel = (DenyListModel) session.getAttribute("denyListModel");
        if (denyListModel == null) {
            return true;
        } else return denyListModel.shouldUserBeRemoved();
    }

    protected void logNewUserAttempt(String email, String password) {
        HttpSession session = getSession();
        UserCacheModel userCacheModel = (UserCacheModel) session.getAttribute("userCacheModel");
        if (userCacheModel != null) {
            if (Boolean.TRUE.equals(userCacheModel.maxLoginAttempts())) {
                addMemberToDenyList(email, password);
            } else {
                Integer loginAttempts = userCacheModel.getLoginAttempts();
                userCacheModel.setLoginAttempts(loginAttempts + 1);
            }
        } else {
            session.setAttribute("userCacheModel", new UserCacheModel(email, password));
        }
    }

    protected Members isMemberAuthenticated(String authToken) {
        authToken = authToken.replaceFirst(AUTH_HEADER_PREFIX, "");
        byte[] decodedBytes = Base64.getDecoder().decode(authToken);
        String decodedString;
        String username = "";
        String password = "";
        try {
            decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            username = tokenizer.nextToken();
            password = tokenizer.nextToken();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return verifyEmailAndPassword(username, password);
    }

    protected void checkForUserRole(Members members, MemberRoleTypes memberRoleTypes) {
        List<MemberRole> userRoles = memberRoleService.getMemberRoles(members.getId());

        if (userRoles.stream().noneMatch(it -> it.getId().getRole().equals(memberRoleTypes))) {
            throw new ResponseStatusException(FORBIDDEN, "You are not authorized to perform this action.");
        }
    }
}
