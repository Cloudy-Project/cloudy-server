package com.cloudy.server.member.domain;

import com.cloudy.server.member.repository.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Member {
    private Long id;
    private String name;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    @Builder
    public Member(Long id, String name, String username, String email, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }
}
