package com.example.demoex.controller;

import com.example.demoex.model.Books;
import com.example.demoex.service.BooksService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/book/{bookId}")
public class BookController {
    private final BooksService booksService;

    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Books getBook(@PathVariable(value = "bookId") Long bookId) {
        checkForTheBook(bookId);
        return booksService.getBook(bookId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Books editBook(@PathVariable(value = "bookId") Long bookId,
                          @RequestBody Books book) {
        checkForTheBook(bookId);
        booksService.verifyBook(book);

        return booksService.editBook(book, bookId);
    }

    private void checkForTheBook(Long bookId) {
        if (booksService.bookExists(bookId)) {
            throw new ResponseStatusException(NOT_FOUND, "Book is not found.");
        }
    }
}
