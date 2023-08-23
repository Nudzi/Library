package com.example.demoex.resource;

import com.example.demoex.model.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRoleDao extends JpaRepository<MemberRole, Long> {
    @Query("select mr from MemberRole mr " +
            "where mr.id.memberId = :memberId")
    List<MemberRole> findByUserId(Long memberId);

    @Query("select mr from MemberRole mr " +
            "where mr.id.role = :role")
    List<MemberRole> findByRoleId(String role);
}