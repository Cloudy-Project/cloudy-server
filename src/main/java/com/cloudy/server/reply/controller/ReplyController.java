package com.cloudy.server.reply.controller;

import com.cloudy.server.reply.domain.Reply;
import com.cloudy.server.reply.dto.request.ReplyCreateRequest;
import com.cloudy.server.reply.dto.request.ReplyUpdateRequest;
import com.cloudy.server.reply.dto.response.ReplyResponse;
import com.cloudy.server.reply.service.ReplyService;
import com.cloudy.server.auth.security.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController implements ReplyApi {
    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponse> create(
            @LoginMember Long id,
            @RequestBody ReplyCreateRequest replyCreateRequest) {
        Reply reply = replyService.create(id, replyCreateRequest);
        ReplyResponse res = new ReplyResponse(
                reply.getId(),
                reply.getLetter().getId(),
                reply.getMember().getName(),
                reply.getContent());
        return ResponseEntity.ok().body(res);
    }

    @PatchMapping
    public ResponseEntity<ReplyResponse> update(
            @LoginMember Long id,
            @RequestBody ReplyUpdateRequest replyUpdateRequest) {
        Reply reply = replyService.update(id, replyUpdateRequest);
        ReplyResponse res = new ReplyResponse(
                reply.getId(),
                reply.getLetter().getId(),
                reply.getMember().getName(),
                reply.getContent());
        return ResponseEntity.ok().body(res);
    }
}
