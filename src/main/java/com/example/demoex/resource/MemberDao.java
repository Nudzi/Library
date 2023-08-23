package com.example.demoex.resource;

import com.example.demoex.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDao extends JpaRepository<Members, Long> {
    Optional<Members> findById(Long id);

    Optional<Members> findByEmailAddress(String emailAddress);

//    Optional<Members> findByEmailAddressAndPassword(String emailAddress, String password);

    boolean existsById(Long id);
}
