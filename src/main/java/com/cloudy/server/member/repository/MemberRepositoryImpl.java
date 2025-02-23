package com.cloudy.server.member.repository;

import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.dto.request.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(MemberEntity.from(member)).toModel();
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberJpaRepository.findByUsername(username).map(MemberEntity::toModel);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id).map(MemberEntity::toModel);
    }

    @Override
    public Optional<Member> update(Long memberId, String name) {
        return memberJpaRepository.findById(memberId)
                .map(me -> me.update(name).toModel());
    }

    @Override
    public void deleteById(Long memberId) {
        memberJpaRepository.deleteById(memberId);
    }
}
