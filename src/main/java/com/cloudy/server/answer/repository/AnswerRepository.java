package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.letter.domain.Letter;

import java.util.Optional;

public interface AnswerRepository {
    Optional<Answer> findByLetterId(Long letterId);
}
