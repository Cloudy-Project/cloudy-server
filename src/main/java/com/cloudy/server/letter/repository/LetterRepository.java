package com.cloudy.server.letter.repository;

import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface LetterRepository {
    void save(Letter letter);

    Optional<Letter> findById(Long letterId);

    List<Letter> findAllByMember(Member member);

    void deleteById(Long letterId);
}
