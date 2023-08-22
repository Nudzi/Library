package com.example.demoex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.FetchType.LAZY;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "transactions")
public final class Transactions {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Members members;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Books books;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate = LocalDateTime.now();

    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "return_date")
    private LocalDateTime returnDate;
}
