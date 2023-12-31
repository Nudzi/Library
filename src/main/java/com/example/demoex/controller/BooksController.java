package com.example.demoex.controller;

import com.example.demoex.model.Books;
import com.example.demoex.service.BooksService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Books> getBooks() {
        return booksService.getBooks();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Books addBook(@RequestBody Books book) {
        booksService.verifyBook(book);
        return booksService.addBook(book);
    }
}
