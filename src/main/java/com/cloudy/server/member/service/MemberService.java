package com.cloudy.server.member.service;

import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.dto.request.MemberUpdateRequest;
import com.cloudy.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member update(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        if (!memberId.equals(memberUpdateRequest.id())) {
            throw new RuntimeException("ONLY MEMBER CAN UPDATE NAME");
        }
        String name = memberUpdateRequest.name();
        if (name.isEmpty()) throw new RuntimeException("NAME NOT ALLOW NULL");
        return memberRepository.update(memberId, name)
                .orElseThrow(() -> new RuntimeException("MEMBER NOT FOUND"));
    }

    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("MEMBER NOT FOUND"));
    }
}
