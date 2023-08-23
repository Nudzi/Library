package com.example.demoex.session.model;

import org.joda.time.Hours;
import org.joda.time.DateTime;

public class DenyListModel {
    private DateTime dateAdded;
    private String email;
    private String password;
    private String ipAddress;

    public DenyListModel(String email, String password, String ipAddress) {
        this.email = email;
        this.password = password;
        this.ipAddress = ipAddress;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public DateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(DateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean shouldUserBeRemoved() {
        return Hours.hoursBetween(DateTime.now(), getDateAdded()).getHours() > 1;
    }
}
