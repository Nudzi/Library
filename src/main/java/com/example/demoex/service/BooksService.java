package com.example.demoex.service;

import com.example.demoex.model.Books;
import com.example.demoex.model.enums.GenreType;
import com.example.demoex.resource.BookDao;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.demoex.util.Utilities.missingRequiredFields;
import static com.example.demoex.util.Utilities.populateBookIdIfNotPresent;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        return !bookDao.existsBooksById(id);
    }

    public Books addBook(Books book) {
        return bookDao.save(book);
    }

    public Books editBook(Books book, Long bookId) {
        populateBookIdIfNotPresent(book, bookId);
        return bookDao.save(book);
    }

    public void deleteBook(Long id) {
        bookDao.deleteById(id);
    }

    public void verifyBook(Books book) {
        if (missingRequiredFields(book)) {
            throw new ResponseStatusException(BAD_REQUEST, "Missing required fields.");
        }

        if (book.checkAvailableAndTotalCopies()) {
            throw new ResponseStatusException(NOT_FOUND, "The ‘available copies’ must be in the " +
                    "smaller amount than ‘total copies’.");
        }

        book.setCopies();
    }
}
