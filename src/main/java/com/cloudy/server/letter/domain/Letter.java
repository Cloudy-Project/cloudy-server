package com.cloudy.server.letter.domain;

import com.cloudy.server.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Letter {
    private Long id;
    private Member member;
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    @Builder
    public Letter(Long id, Member member, String content, String writer, LocalDateTime createdAt) {
        this.id = id;
        this.member = member;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
    }
}
