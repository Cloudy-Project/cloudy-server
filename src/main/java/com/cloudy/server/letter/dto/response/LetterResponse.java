package com.cloudy.server.letter.dto.response;

public record LetterResponse(
        Long id,
        String memberName,
        String content,
        String writer,
        String answerContent) {
}
