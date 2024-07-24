package com.cloudy.server.answer.controller;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.answer.dto.request.AnswerCreateRequest;
import com.cloudy.server.answer.dto.request.AnswerUpdateRequest;
import com.cloudy.server.answer.dto.response.AnswerResponse;
import com.cloudy.server.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController implements AnswerApi {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerResponse> create(@RequestBody AnswerCreateRequest answerCreateRequest) {
        Answer answer = answerService.create(answerCreateRequest);
        AnswerResponse res = new AnswerResponse(
                answer.getId(),
                answer.getLetter().getId(),
                answer.getMember().getName(),
                answer.getContent());
        return ResponseEntity.ok().body(res);
    }

    @PatchMapping
    public ResponseEntity<AnswerResponse> update(@RequestBody AnswerUpdateRequest answerUpdateRequest) {
        Answer answer = answerService.update(answerUpdateRequest);
        AnswerResponse res = new AnswerResponse(
                answer.getId(),
                answer.getLetter().getId(),
                answer.getMember().getName(),
                answer.getContent());
        return ResponseEntity.ok().body(res);
    }
}
