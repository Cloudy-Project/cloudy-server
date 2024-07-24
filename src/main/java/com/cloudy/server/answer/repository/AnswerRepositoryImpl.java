package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.letter.repository.LetterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository {
    private final AnswerJpaRepository answerJpaRepository;

    @Override
    public Optional<Answer> findByLetterId(Long letterId) {
        return answerJpaRepository.findByLetterId(letterId).map(AnswerEntity::toModel);
    }
}
