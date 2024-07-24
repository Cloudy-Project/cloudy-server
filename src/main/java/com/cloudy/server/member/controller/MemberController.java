package com.cloudy.server.member.controller;

import com.cloudy.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberApi {
    private final MemberService memberService;

}
