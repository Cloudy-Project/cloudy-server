package com.cloudy.server.answer.dto.response;

public record ReplyResponse(
        Long id,
        Long letterId,
        String memberName,
        String content) {
}
