package com.example.demoex.model;

import com.example.demoex.model.enums.MemberRoleTypes;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public final class MemberRoleId<T> implements Serializable {

    @NonNull
    @Column(name = "member_id")
    private Long memberId;

    @NonNull
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private MemberRoleTypes role;

    @NonNull
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(@NonNull Long memberId) {
        this.memberId = memberId;
    }

    @NonNull
    public MemberRoleTypes getRole() {
        return role;
    }

    public void setRole(@NonNull MemberRoleTypes role) {
        this.role = role;
    }
}
