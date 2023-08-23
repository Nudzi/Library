package com.example.demoex.model.enums;

public enum MemberRoleTypes {
    ADD_BOOK("Add Book"),
    EDIT_BOOK("Edit Book"),
    DELETE_BOOK("Delete Book"),
    ADD_BOOK_CATEGORY("Add Book Category"),
    EDIT_BOOK_CATEGORY("Edit Book Category"),
    DELETE_BOOK_CATEGORY("Delete Book Category"),
    GET_SESSIONS("Get Sessions"),
    ADMIN("Admin");

    private String name;
    MemberRoleTypes(String name) {
        this.name = (name);
    }
}
