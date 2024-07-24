package com.cloudy.server.letter.controller;

import com.cloudy.server.auth.security.LoginMember;
import com.cloudy.server.letter.dto.request.LetterRequest;
import com.cloudy.server.letter.dto.response.LetterResponse;
import com.cloudy.server.letter.dto.response.LetterSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "편지 (Letter)")
public interface LetterApi {
    @Operation(summary = "편지 작성")
    void create(@RequestBody LetterRequest letterRequest);

    @Operation(summary = "편지 단건 조회")
    LetterResponse findById(@PathVariable("letterId") Long letterId);

    @Operation(summary = "편지 전체 조회")
    List<LetterSimpleResponse> findAllByMember(@PathVariable("memberId") Long memberId);

    @Operation(summary = "편지 삭제")
    void delete(@LoginMember Long memberId, @PathVariable("letterId") Long letterId);
}
