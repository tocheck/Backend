package com.example.yeminhye_project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCategoryRequestDto {
    private String nickname;
    private String categoryName;
    private String hexCode;
}
