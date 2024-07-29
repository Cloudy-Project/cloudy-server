package com.cloudy.server.answer.dto.request;

public record ReplyCreateRequest(
        Long letterId,
        Long memberId,
        String content) {
}
