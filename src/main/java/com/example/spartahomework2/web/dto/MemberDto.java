package com.example.spartahomework2.web.dto;

import com.example.spartahomework2.domain.entity.Member;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public class MemberDto {

    @Getter
    public static class LoginReq{
        private final String nickname;

        private final String password;

        @JsonCreator
        public LoginReq(String nickname, String password) {
            this.nickname = nickname;
            this.password = password;
        }



    }

    @Getter
    public static class SignupReq{

        private final String nickname;

        private final String password;

        private final String passwordCheck;

        @JsonCreator
        public SignupReq(String nickname, String password, String passwordCheck){
            this.nickname = nickname;
            this.password = password;
            this.passwordCheck = passwordCheck;
        }

        public Member toEntity(String encodedPassword){
            return new Member(this.nickname, encodedPassword);
        }
    }
}
