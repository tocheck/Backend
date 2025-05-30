package com.example.yeminhye_project.dto;

import com.example.yeminhye_project.entity.Element;
import com.example.yeminhye_project.entity.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoListRequestDto {
    private String nickname;
    private String todoListTitle;
    private LocalDate startDate;

    private Element element;
    private String newElement; // 날짜는 yyyy-mm-dd 형식이어야 함.
}
