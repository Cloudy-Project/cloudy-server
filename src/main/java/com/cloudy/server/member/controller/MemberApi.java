package com.cloudy.server.member.controller;

import com.cloudy.server.auth.security.LoginMember;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.dto.request.MemberUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 (Member)")
public interface MemberApi {
    @Operation(summary = "회원 정보 수정")
    ResponseEntity<Member> update(
            @LoginMember Long memberId,
            @RequestBody MemberUpdateRequest memberUpdateRequest);

    @Operation(summary = "회원 탈퇴")
    ResponseEntity<?> delete(@LoginMember Long memberId);
}
