package com.example.demoex.controller;

import com.example.demoex.model.Books;
import com.example.demoex.model.Members;
import com.example.demoex.service.BooksService;
import com.example.demoex.service.MemberService;
import com.example.demoex.service.memberRole.MemberRoleService;
import com.example.demoex.session.BaseAuthResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.demoex.model.enums.MemberRoleTypes.ADMIN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/book/{bookId}")
public class BookController extends BaseAuthResource {
    private final BooksService booksService;

    public BookController(BooksService booksService,
                          HttpServletResponse response, HttpServletRequest request,
                          MemberService memberService, MemberRoleService memberRoleService) {
        super(memberService, request, response, memberRoleService);
        this.booksService = booksService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Books getBook(@PathVariable(value = "bookId") Long bookId) {
        checkForTheBook(bookId);
        return booksService.getBook(bookId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Books editBook(@PathVariable(value = "bookId") Long bookId,
                          @RequestBody Books book,
                          @RequestHeader(value = AUTHORIZATION, required = false, defaultValue = "") String authorization) {

        try {
            if (!authorization.isEmpty()) {
                Members member = isMemberAuthenticated(authorization);
                checkForUserRole(member, ADMIN);
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(FORBIDDEN, "You are not authorized to perform this action.");
        }

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
