package com.cloudy.server.letter.controller;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.answer.service.AnswerService;
import com.cloudy.server.auth.security.LoginMember;
import com.cloudy.server.letter.domain.Letter;
import com.cloudy.server.letter.dto.request.LetterRequest;
import com.cloudy.server.letter.dto.response.LetterResponse;
import com.cloudy.server.letter.dto.response.LetterSimpleResponse;
import com.cloudy.server.letter.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letter")
@RequiredArgsConstructor
public class LetterController implements LetterApi {
    private final LetterService letterService;
    private final AnswerService answerService;

    @PostMapping
    public void create(@RequestBody LetterRequest letterRequest) {
        letterService.create(letterRequest);
    }

    @GetMapping("/{letterId}")
    public LetterResponse findById(@PathVariable("letterId") Long letterId) {
        Letter letter = letterService.findById(letterId);
        Answer answer = answerService.findByLetter(letter);
        String answerContent = answer != null ? answer.getContent() : null;
        return new LetterResponse(letter.getId(), letter.getMember().getName(), letter.getContent(), letter.getWriter(), answerContent);
    }

    @GetMapping("/member/{memberId}")
    public List<LetterSimpleResponse> findAllByMember(@PathVariable("memberId") Long memberId) {
        List<Letter> letters = letterService.findAllByMember(memberId);
        List<LetterSimpleResponse> res = letters
                .stream()
                .map(letter -> new LetterSimpleResponse(letter.getId(), letter.getWriter())).toList();
        return res;
    }

    @DeleteMapping("/{letterId}")
    public void delete(@LoginMember Long memberId, @PathVariable("letterId") Long letterId) {
        letterService.delete(memberId, letterId);
    }
}
