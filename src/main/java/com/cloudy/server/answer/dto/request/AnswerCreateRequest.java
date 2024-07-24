package com.cloudy.server.answer.dto.request;

public record AnswerCreateRequest(
        Long letterId,
        Long memberId,
        String content) {
}
