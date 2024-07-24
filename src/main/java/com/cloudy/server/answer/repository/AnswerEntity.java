package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.letter.repository.LetterEntity;
import com.cloudy.server.member.repository.MemberEntity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private LetterEntity letter;
    @ManyToOne
    private MemberEntity member;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;

    public static AnswerEntity from(Answer answer) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.letter = LetterEntity.from(answer.getLetter());
        answerEntity.member = MemberEntity.from(answer.getMember());
        answerEntity.content = answer.getContent();

        return answerEntity;
    }

    public Answer toModel() {
        return Answer.builder()
                .id(id)
                .letter(letter.toModel())
                .member(member.toModel())
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
