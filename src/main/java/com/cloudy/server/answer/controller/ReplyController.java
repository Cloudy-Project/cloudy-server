package com.cloudy.server.answer.controller;

import com.cloudy.server.answer.domain.Reply;
import com.cloudy.server.answer.dto.request.ReplyCreateRequest;
import com.cloudy.server.answer.dto.request.ReplyUpdateRequest;
import com.cloudy.server.answer.dto.response.ReplyResponse;
import com.cloudy.server.answer.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController implements ReplyApi {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponse> create(@RequestBody ReplyCreateRequest replyCreateRequest) {
        Reply reply = replyService.create(replyCreateRequest);
        ReplyResponse res = new ReplyResponse(
                reply.getId(),
                reply.getLetter().getId(),
                reply.getMember().getName(),
                reply.getContent());
        return ResponseEntity.ok().body(res);
    }

    @PatchMapping
    public ResponseEntity<ReplyResponse> update(@RequestBody ReplyUpdateRequest replyUpdateRequest) {
        Reply reply = replyService.update(replyUpdateRequest);
        ReplyResponse res = new ReplyResponse(
                reply.getId(),
                reply.getLetter().getId(),
                reply.getMember().getName(),
                reply.getContent());
        return ResponseEntity.ok().body(res);
    }
}
