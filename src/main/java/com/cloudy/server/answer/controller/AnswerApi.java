package com.cloudy.server.answer.controller;

import com.cloudy.server.answer.dto.request.AnswerCreateRequest;
import com.cloudy.server.answer.dto.request.AnswerUpdateRequest;
import com.cloudy.server.answer.dto.response.AnswerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "답장 (Answer)")
public interface AnswerApi {
    @Operation(summary = "답장 작성")
    ResponseEntity<AnswerResponse> create(@RequestBody AnswerCreateRequest answerCreateRequest);

    @Operation(summary = "답장 수정")
    ResponseEntity<AnswerResponse> update(@RequestBody AnswerUpdateRequest answerUpdateRequest);
}
