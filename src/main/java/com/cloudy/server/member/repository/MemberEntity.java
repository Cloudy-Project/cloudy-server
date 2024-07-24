package com.cloudy.server.member.repository;

import com.cloudy.server.member.domain.Member;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    @CreatedDate
    private LocalDateTime createdAt;

    public static MemberEntity from(Member member) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = member.getId();
        memberEntity.name = member.getName();
        memberEntity.username = member.getUsername();
        memberEntity.email = member.getEmail();

        return memberEntity;
    }

    public Member toModel() {
        return Member.builder()
                .id(id)
                .name(name)
                .username(username)
                .email(email)
                .createdAt(createdAt)
                .build();
    }
}
