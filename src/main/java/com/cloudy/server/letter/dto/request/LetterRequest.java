package com.cloudy.server.letter.dto.request;

public record LetterRequest(
        Long memberId,
        String content,
        String writer) {
}
