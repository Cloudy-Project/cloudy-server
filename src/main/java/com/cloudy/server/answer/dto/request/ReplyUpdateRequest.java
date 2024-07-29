package com.cloudy.server.answer.dto.request;

public record ReplyUpdateRequest(
        Long id,
        String content) {
}
