package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.answer.dto.request.AnswerUpdateRequest;

import java.util.Optional;

public interface AnswerRepository {
    Optional<Answer> findByLetterId(Long letterId);

    Answer save(Answer answer);

    Optional<Answer> findById(Long id);

    Optional<Answer> update(AnswerUpdateRequest answerUpdateRequest);
}
