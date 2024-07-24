package com.cloudy.server.answer.dto.request;

public record AnswerUpdateRequest(
        Long id,
        String content) {
}
