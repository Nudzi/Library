package com.example.demoex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "members")
public final class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nullable
    @Column(name = "first_name")
    private String firstName;

    @Nullable
    @Column(name = "last_name")
    private String lastName;

    @Nullable
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "contact_number")
    private Integer contactNumber;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Nullable
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @JsonIgnore
    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private List<Transactions> transactions;
}
