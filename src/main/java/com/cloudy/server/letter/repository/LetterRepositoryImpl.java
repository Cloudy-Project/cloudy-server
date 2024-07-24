package com.cloudy.server.letter.repository;

import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.repository.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LetterRepositoryImpl implements LetterRepository {
    private final LetterJpaRepository letterJpaRepository;

    @Override
    public void save(Letter letter) {
        letterJpaRepository.save(LetterEntity.from(letter));
    }

    @Override
    public Optional<Letter> findById(Long letterId) {
        return letterJpaRepository.findById(letterId).map(LetterEntity::toModel);
    }

    @Override
    public List<Letter> findAllByMember(Member member) {
        return letterJpaRepository
                .findAllByMember(MemberEntity.from(member))
                .stream().map(LetterEntity::toModel).toList();
    }

    @Override
    public void deleteById(Long letterId) {
        letterJpaRepository.deleteById(letterId);
    }
}
