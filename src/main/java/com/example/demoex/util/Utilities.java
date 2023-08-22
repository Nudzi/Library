package com.example.demoex.util;

import com.example.demoex.model.Books;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * Utility methods for the application.
 */
public class Utilities {

    private Utilities() {
    }

    public static void populateBookIdIfNotPresent(Books book, Long bookId) {
        if (bookId != null) {
            book.setId(bookId);
        } else if (book.getId() == null) {
            book.setId(Long.parseLong(UUID.randomUUID().toString()));
        }
    }

    public static boolean missingRequiredFields(Books book) {
        return areAnyEmpty(book.getTitle()) || book.getIsbn() == null;
    }

    public static boolean areAnyEmpty(String... fields) {
        return Stream.of(fields).anyMatch(StringUtils::isEmpty);
    }
}
