package com.cloudy.server.answer.dto.response;

public record AnswerResponse(
        Long id,
        Long letterId,
        String memberName,
        String content) {
}
