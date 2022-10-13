package com.example.spartahomework2.web.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ResponseDto<T> {

    private final Boolean success;

    private final T data;

    public ResponseDto(boolean success, T data){
        this.success = success;
        this.data = data;
    }
}
