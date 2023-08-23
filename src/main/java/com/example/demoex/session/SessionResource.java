package com.example.demoex.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionResource extends SessionManagement {
    public SessionResource(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }
}
