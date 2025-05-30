package com.example.yeminhye_project.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoListRequestDto {
    private String nickname;
    private String categoryName;
    private String todoListTitle;
    private LocalDate startDate;
    private LocalDate endDate;
}
