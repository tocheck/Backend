package com.example.yeminhye_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor // 생성자 주입
@ToString
public class ErrorResponseDto {
    private boolean isSuccess;
    private String message;
    private String error;
}
