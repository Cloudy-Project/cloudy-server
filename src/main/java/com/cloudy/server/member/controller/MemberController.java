package com.cloudy.server.member.controller;

import com.cloudy.server.auth.security.LoginMember;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.dto.request.MemberUpdateRequest;
import com.cloudy.server.member.dto.response.MemberSimpleResponse;
import com.cloudy.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController implements MemberApi {
    private final MemberService memberService;

    @PatchMapping
    public ResponseEntity<Member> update(
            @LoginMember Long memberId,
            @RequestBody MemberUpdateRequest memberUpdateRequest) {
        Member member = memberService.update(memberId, memberUpdateRequest);
        return ResponseEntity.ok().body(member);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@LoginMember Long memberId) {
        memberService.deleteById(memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberSimpleResponse> findById(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findById(memberId);
        MemberSimpleResponse res = new MemberSimpleResponse(member.getId(), member.getName());
        return ResponseEntity.ok().body(res);
    }
}
