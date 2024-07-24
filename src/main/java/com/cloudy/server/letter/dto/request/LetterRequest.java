package com.cloudy.server.letter.dto.request;

import com.cloudy.server.member.repository.MemberEntity;

public record LetterRequest(
        Long memberId,
        String content,
        String writer) {
}
