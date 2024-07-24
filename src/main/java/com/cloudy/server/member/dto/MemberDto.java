package com.cloudy.server.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {
    Long id;
    String name;
    String username;
    String email;

    @Builder
    public MemberDto(Long id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }
}
