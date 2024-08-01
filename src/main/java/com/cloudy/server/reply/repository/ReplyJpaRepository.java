package com.cloudy.server.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyJpaRepository extends JpaRepository<ReplyEntity, Long> {
    Optional<ReplyEntity> findByLetterId(Long letterId);

    Optional<ReplyEntity> findByIdAndMemberId(Long id, Long memberId);
}
