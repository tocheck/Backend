package com.example.yeminhye_project.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class SuccessResponseDto<T> {
    private boolean isSuccess;
    private String message;
    private T data;
}
