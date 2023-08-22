package com.example.demoex.service;

import com.example.demoex.model.Books;
import com.example.demoex.model.enums.GenreType;
import com.example.demoex.resource.BookDao;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demoex.util.Utilities.populateBookIdIfNotPresent;

@Service
public class BooksService {

    private final BookDao bookDao;

    public BooksService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Books> getBooks() {
        return bookDao.findAll();
    }

    public List<Books> bookHasGenre(GenreType genreType) {
        return bookDao.findAllByGenre(genreType);
    }

    public Books getBook(Long id) {
        return bookDao.findBooksById(id);
    }

    public boolean bookExists(Long id) {
        return bookDao.existsBooksById(id);
    }

    public Books addBook(Books book) {
        populateBookIdIfNotPresent(book);
        return bookDao.save(book);
    }

    public void deleteBook(Long id) {
        bookDao.deleteById(id);
    }
}