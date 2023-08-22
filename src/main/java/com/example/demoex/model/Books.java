package com.example.demoex.model;

import com.example.demoex.model.enums.GenreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private Integer isbn;

    @Nullable
    @Column(name = "genre")
    private GenreType genre;

    @Nullable
    @Column(name = "total_copies")
    private Integer totalCopies;
    @Nullable
    @Column(name = "available_copies")
    private Integer availableCopies;
    @Nullable
    @Column(name = "publisher")
    private String publisher;
    @Nullable
    @Column(name = "publish_year")
    private LocalDateTime publishYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(@Nullable GenreType genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    @Nullable
    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(@Nullable Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    @Nullable
    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(@Nullable Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Nullable
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(@Nullable String publisher) {
        this.publisher = publisher;
    }

    @Nullable
    public LocalDateTime getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(@Nullable LocalDateTime publishYear) {
        this.publishYear = publishYear;
    }
}
