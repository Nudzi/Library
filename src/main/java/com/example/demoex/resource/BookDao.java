package com.example.demoex.resource;

import com.example.demoex.model.Books;
import com.example.demoex.model.enums.GenreType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Books, Long> {

    List<Books> findAllByGenre(GenreType genreType);

    boolean existsBooksById(Long id);

    Books findBooksById(Long id);
}
