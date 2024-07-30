package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Reply;
import com.cloudy.server.letter.repository.LetterEntity;
import com.cloudy.server.member.repository.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LetterEntity letter;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MemberEntity member;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;

    public static ReplyEntity from(Reply reply) {
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.letter = LetterEntity.from(reply.getLetter());
        replyEntity.member = MemberEntity.from(reply.getMember());
        replyEntity.content = reply.getContent();

        return replyEntity;
    }

    public Reply toModel() {
        return Reply.builder()
                .id(id)
                .letter(letter.toModel())
                .member(member.toModel())
                .content(content)
                .createdAt(createdAt)
                .build();
    }

    public ReplyEntity update(String content) {
        this.content = content;
        return this;
    }
}
