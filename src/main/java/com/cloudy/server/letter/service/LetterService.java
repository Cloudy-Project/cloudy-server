package com.cloudy.server.letter.service;

import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.letter.dto.request.LetterRequest;
import com.cloudy.server.letter.repository.LetterRepository;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {
    private final MemberRepository memberRepository;
    private final LetterRepository letterRepository;

    @Transactional
    public void create(LetterRequest letterRequest) {
        Member member = memberRepository.findById(letterRequest.memberId())
                .orElseThrow(() -> new RuntimeException("MEMBER NOT FOUND"));
        Letter letter = Letter.builder()
                        .member(member)
                        .content(letterRequest.content())
                        .writer(letterRequest.writer())
                        .build();
        letterRepository.save(letter);
    }

    public Letter findById(Long letterId) {
        return letterRepository.findById(letterId)
                .orElseThrow(() -> new RuntimeException("LETTER NOT FOUND"));
    }

    @Transactional
    public List<Letter> findAllByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("MEMBER NOT FOUND"));
        return letterRepository.findAllByMember(member);
    }

    @Transactional
    public void delete(Long memberId, Long letterId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("MEMBER NOT FOUND"));
        Letter letter = findById(letterId);
        if (memberId.equals(letter.getMember().getId())) {
            letterRepository.deleteById(letterId);
        } else {
            throw new RuntimeException("ONLY MEMBER CAN DELETE LETTER");
        }
    }
}
