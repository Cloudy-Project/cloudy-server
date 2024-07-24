package com.cloudy.server.member.repository;

import com.cloudy.server.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member user);

    Optional<Member> findByUsername(String username);
}
