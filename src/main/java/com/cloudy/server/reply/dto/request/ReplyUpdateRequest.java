package com.cloudy.server.reply.dto.request;

public record ReplyUpdateRequest(
        Long id,
        String content) {
}
