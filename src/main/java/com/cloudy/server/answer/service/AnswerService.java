package com.cloudy.server.answer.service;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.answer.dto.request.AnswerCreateRequest;
import com.cloudy.server.answer.dto.request.AnswerUpdateRequest;
import com.cloudy.server.answer.repository.AnswerRepository;
import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.letter.repository.LetterRepository;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;

    public Answer findByLetter(Letter letter) {
        return answerRepository.findByLetterId(letter.getId()).orElse(null);
    }

    @Transactional
    public Answer create(AnswerCreateRequest answerCreateRequest) {
        Letter letter = letterRepository.findById(answerCreateRequest.letterId())
                .orElseThrow(() -> new RuntimeException("LETTER NOT FOUND"));
        Member member = memberRepository.findById(answerCreateRequest.memberId())
                .orElseThrow(() -> new RuntimeException("MEMBER NOT FOUND"));
        Answer answer = Answer.builder()
                .letter(letter)
                .member(member)
                .content(answerCreateRequest.content())
                .build();
        return answerRepository.save(answer);
    }

    @Transactional
    public Answer update(AnswerUpdateRequest answerUpdateRequest) {
        return answerRepository.update(answerUpdateRequest)
                .orElseThrow(() -> new RuntimeException("ANSWER NOT FOUND"));
    }
}
