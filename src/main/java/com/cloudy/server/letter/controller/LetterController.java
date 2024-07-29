package com.cloudy.server.letter.controller;

import com.cloudy.server.answer.domain.Reply;
import com.cloudy.server.answer.service.ReplyService;
import com.cloudy.server.auth.security.LoginMember;
import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.letter.dto.request.LetterRequest;
import com.cloudy.server.letter.dto.response.LetterResponse;
import com.cloudy.server.letter.dto.response.LetterSimpleResponse;
import com.cloudy.server.letter.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letter")
@RequiredArgsConstructor
public class LetterController implements LetterApi {
    private final LetterService letterService;
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LetterRequest letterRequest) {
        letterService.create(letterRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{letterId}")
    public ResponseEntity<LetterResponse> findById(@PathVariable("letterId") Long letterId) {
        Letter letter = letterService.findById(letterId);
        Reply reply = replyService.findByLetter(letter);
        String answerContent = reply != null ? reply.getContent() : null;
        Long answerId = reply != null ? reply.getId() : null;
        LetterResponse res = new LetterResponse(
                letter.getId(),
                letter.getMember().getId(),
                letter.getMember().getName(),
                letter.getContent(),
                letter.getWriter(),
                answerId,
                answerContent);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<LetterSimpleResponse>> findAllByMember(@PathVariable("memberId") Long memberId) {
        List<Letter> letters = letterService.findAllByMember(memberId);
        List<LetterSimpleResponse> res = letters
                .stream()
                .map(letter -> new LetterSimpleResponse(letter.getId(), letter.getWriter())).toList();
        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/{letterId}")
    public ResponseEntity<?> delete(@LoginMember Long memberId, @PathVariable("letterId") Long letterId) {
        letterService.delete(memberId, letterId);
        return ResponseEntity.ok().build();
    }
}
