package com.cloudy.server.answer.repository;

import com.cloudy.server.letter.repository.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerJpaRepository extends JpaRepository<AnswerEntity, Long> {
    Optional<AnswerEntity> findByLetterId(Long letterId);
}
