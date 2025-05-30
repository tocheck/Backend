package com.example.yeminhye_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SuccessResponseDto<T> {
    private boolean isSuccess;
    private String message;
    private T data;
}
