package com.example.yeminhye_project.dto;

import com.example.yeminhye_project.entity.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoListsResponseDto {
    private String categoryName;
    private String hexCode;
    private String todoListTitle;
    private Status status;
    private LocalDate startDate;
    private LocalDate endDate;
}
