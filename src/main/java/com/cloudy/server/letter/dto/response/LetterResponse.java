package com.cloudy.server.letter.dto.response;

public record LetterResponse(
        Long id,
        Long memberId,
        String memberName,
        String content,
        String writer,
        Long answerId,
        String answerContent) {
}
