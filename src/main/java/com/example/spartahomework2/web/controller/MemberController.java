package com.example.spartahomework2.web.controller;

import com.example.spartahomework2.domain.service.MemberService;
import com.example.spartahomework2.security.jwt.TokenProvider;
import com.example.spartahomework2.web.dto.ResponseDto;
import com.example.spartahomework2.web.dto.TokenDto;
import com.example.spartahomework2.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody MemberDto.SignupReq reqDto){

        memberService.signup(reqDto);

        return new ResponseDto<>(true, null);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody MemberDto.LoginReq reqDto, HttpServletResponse res){

        TokenDto tokenDto = memberService.login(reqDto);

        res.addHeader(TokenProvider.AUTHORIZATION_HEADER, tokenDto.getAccessToken());

        return new ResponseDto<>(true, null);
    }

}
