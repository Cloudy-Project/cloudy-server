package com.cloudy.server.answer.controller;

import com.cloudy.server.answer.dto.request.ReplyCreateRequest;
import com.cloudy.server.answer.dto.request.ReplyUpdateRequest;
import com.cloudy.server.answer.dto.response.ReplyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "답장 (Answer)")
public interface ReplyApi {
    @Operation(summary = "답장 작성")
    ResponseEntity<ReplyResponse> create(@RequestBody ReplyCreateRequest replyCreateRequest);

    @Operation(summary = "답장 수정")
    ResponseEntity<ReplyResponse> update(@RequestBody ReplyUpdateRequest replyUpdateRequest);
}
