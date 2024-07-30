package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Reply;
import com.cloudy.server.answer.dto.request.ReplyUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {
    private final ReplyJpaRepository replyJpaRepository;

    @Override
    public Optional<Reply> findByLetterId(Long letterId) {
        return replyJpaRepository.findByLetterId(letterId).map(ReplyEntity::toModel);
    }

    @Override
    public Reply save(Reply reply) {
        return replyJpaRepository.save(ReplyEntity.from(reply)).toModel();
    }

    @Override
    public Optional<Reply> findById(Long id) {
        return replyJpaRepository.findById(id).map(ReplyEntity::toModel);
    }

    @Override
    public Optional<Reply> update(Long id, ReplyUpdateRequest replyUpdateRequest) {
        return replyJpaRepository.findByIdAndMemberId(replyUpdateRequest.id(), id)
                .map(ae -> ae.update(replyUpdateRequest.content()).toModel());
    }
}
