package com.example.yeminhye_project.controller;

import com.example.yeminhye_project.dto.ErrorResponseDto;
import com.example.yeminhye_project.dto.SuccessResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ControllerUtils {
    // 시스템 내부 오류 예외 처리 메서드
    public ResponseEntity<ErrorResponseDto> assertBySystem(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest()
                .contentType(MediaType.valueOf("application/json;charset=UTF-8"))
                .body(new ErrorResponseDto(false, "시스템 내부적으로 오류가 발생했습니다", e.getMessage()));
    }

    // 정상 흐름
    public <T> ResponseEntity<SuccessResponseDto<T>> getSuccessResponse(String message, T data) {
        System.out.println(message);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("application/json;charset=UTF-8"))
                .body(new SuccessResponseDto<T>(true, message, data));
    }
}
