package com.cloudy.server.reply.dto.response;

public record ReplyResponse(
        Long id,
        Long letterId,
        String memberName,
        String content) {
}
