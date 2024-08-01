package com.cloudy.server.reply.service;

import com.cloudy.server.reply.domain.Reply;
import com.cloudy.server.reply.dto.request.ReplyCreateRequest;
import com.cloudy.server.reply.dto.request.ReplyUpdateRequest;
import com.cloudy.server.reply.repository.ReplyRepository;
import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.letter.repository.LetterRepository;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;

    public Reply findByLetter(Letter letter) {
        return replyRepository.findByLetterId(letter.getId()).orElse(null);
    }

    @Transactional
    public Reply create(Long id, ReplyCreateRequest replyCreateRequest) {
        Letter letter = letterRepository.findById(replyCreateRequest.letterId())
                .orElseThrow(() -> new RuntimeException("LETTER NOT FOUND"));
        if (!letter.getMember().getId().equals(id)) {
            throw new RuntimeException("ONLY LETTER OWNER CAN REPLY");
        }
        Member member = memberRepository.findById(replyCreateRequest.memberId())
                .orElseThrow(() -> new RuntimeException("MEMBER NOT FOUND"));
        Reply reply = Reply.builder()
                .letter(letter)
                .member(member)
                .content(replyCreateRequest.content())
                .build();
        return replyRepository.save(reply);
    }

    @Transactional
    public Reply update(Long id, ReplyUpdateRequest replyUpdateRequest) {
        return replyRepository.update(id, replyUpdateRequest)
                .orElseThrow(() -> new RuntimeException("ANSWER NOT FOUND"));
    }
}
