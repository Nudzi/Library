package com.example.demoex.util;

import com.example.demoex.model.Books;

import java.util.UUID;

/**
 * Utility methods for the application.
 */
public class Utilities {

    private Utilities() {
    }

    public static Long populateBookIdIfNotPresent(Books book) {
        if (book.getId() == null) {
            book.setId(Long.parseLong(UUID.randomUUID().toString()));
        }
        return book.getId();
    }
}
