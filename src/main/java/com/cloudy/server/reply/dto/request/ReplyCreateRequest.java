package com.cloudy.server.reply.dto.request;

public record ReplyCreateRequest(
        Long letterId,
        Long memberId,
        String content) {
}
