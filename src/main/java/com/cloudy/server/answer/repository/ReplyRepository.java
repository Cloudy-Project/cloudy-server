package com.cloudy.server.answer.repository;

import com.cloudy.server.answer.domain.Reply;
import com.cloudy.server.answer.dto.request.ReplyUpdateRequest;

import java.util.Optional;

public interface ReplyRepository {
    Optional<Reply> findByLetterId(Long letterId);

    Reply save(Reply reply);

    Optional<Reply> findById(Long id);

    Optional<Reply> update(ReplyUpdateRequest replyUpdateRequest);
}
