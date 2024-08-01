package com.cloudy.server.reply.repository;

import com.cloudy.server.reply.domain.Reply;
import com.cloudy.server.reply.dto.request.ReplyUpdateRequest;

import java.util.Optional;

public interface ReplyRepository {
    Optional<Reply> findByLetterId(Long letterId);

    Reply save(Reply reply);

    Optional<Reply> findById(Long id);

    Optional<Reply> update(Long id, ReplyUpdateRequest replyUpdateRequest);
}
