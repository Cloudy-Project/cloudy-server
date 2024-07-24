package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.answer.dto.request.AnswerUpdateRequest;
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

    @Override
    public Answer save(Answer answer) {
        return answerJpaRepository.save(AnswerEntity.from(answer)).toModel();
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return answerJpaRepository.findById(id).map(AnswerEntity::toModel);
    }

    @Override
    public Optional<Answer> update(AnswerUpdateRequest answerUpdateRequest) {
        return answerJpaRepository.findById(answerUpdateRequest.id())
                .map(ae -> ae.update(answerUpdateRequest.content()).toModel());
    }
}
