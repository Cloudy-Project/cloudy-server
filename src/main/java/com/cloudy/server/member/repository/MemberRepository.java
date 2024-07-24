package com.cloudy.server.member.repository;

import com.cloudy.server.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findByUsername(String username);

    Optional<Member> findById(Long id);
}
