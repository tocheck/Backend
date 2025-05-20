package com.example.yeminhye_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateHexCodeRequestDto {
    private String nickname;
    private String categoryName;
    private String newHexCode;
}
