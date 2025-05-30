package com.example.yeminhye_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponseDto {
    private boolean isSuccess;
    private String message;
    private String error;
}
