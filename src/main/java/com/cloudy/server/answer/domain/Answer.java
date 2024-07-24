package com.cloudy.server.answer.domain;

import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Answer {
    private Long id;
    private Letter letter;
    private Member member;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public Answer(Long id, Letter letter, Member member, String content, LocalDateTime createdAt) {
        this.id = id;
        this.letter = letter;
        this.member = member;
        this.content = content;
        this.createdAt = createdAt;
    }
}
