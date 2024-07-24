package com.cloudy.server.letter.repository;

import com.cloudy.server.member.repository.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LetterJpaRepository extends JpaRepository<LetterEntity, Long> {
    List<LetterEntity> findAllByMember(MemberEntity member);
}
