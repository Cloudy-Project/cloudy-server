package com.cloudy.server.letter.repository;

import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.repository.MemberEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class LetterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MemberEntity member;
    private String content;
    private String writer;
    @CreatedDate
    private LocalDateTime createdAt;

    public static LetterEntity from(Letter letter) {
        LetterEntity letterEntity = new LetterEntity();
        letterEntity.id = letter.getId();
        letterEntity.member = MemberEntity.from(letter.getMember());
        letterEntity.content = letter.getContent();
        letterEntity.writer = letter.getWriter();
        letterEntity.createdAt = letter.getCreatedAt();

        return letterEntity;
    }

    public Letter toModel() {
        return Letter.builder()
                .id(id)
                .member(member.toModel())
                .content(content)
                .writer(writer)
                .createdAt(createdAt)
                .build();
    }
}
