package com.example.demoex.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "member_role")
public final class MemberRole {
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "member_id", column = @Column(name = "member_id")),
            @AttributeOverride(name = "role", column = @Column(name = "role"))
    })
    @JsonProperty("member_role_id")
    private MemberRoleId<Long> id;

    public MemberRoleId<Long> getId() {
        return id;
    }

    public void setId(MemberRoleId<Long> id) {
        this.id = id;
    }
}
