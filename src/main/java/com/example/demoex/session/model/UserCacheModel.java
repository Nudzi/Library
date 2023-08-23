package com.example.demoex.session.model;

public class UserCacheModel {
    private String email;
    private String password;
    private Integer loginAttempts;

    public UserCacheModel(String email, String password) {
        this.email = email;
        this.password = password;
        this.loginAttempts = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(Integer loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    private static final long MAX_FAILED_ATTEMPTS = 5;

    public Boolean maxLoginAttempts() {
        return loginAttempts == MAX_FAILED_ATTEMPTS;
    }
}
