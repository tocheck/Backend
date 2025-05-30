package com.example.yeminhye_project.controller;

import com.example.yeminhye_project.dto.CreateTodoListRequestDto;
import com.example.yeminhye_project.dto.TodoListsResponseDto;
import com.example.yeminhye_project.dto.UpdateTodoListRequestDto;
import com.example.yeminhye_project.entity.Element;
import com.example.yeminhye_project.entity.Status;
import com.example.yeminhye_project.entity.TodoLists;
import com.example.yeminhye_project.service.TodoListsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todoLists")
public class TodoListsController {
    private final ControllerUtils utils;
    private final TodoListsService todoListsService;

    // 투두리스트 추가
    @PostMapping("/create")
    public ResponseEntity<?> createTodoList(@RequestBody List<CreateTodoListRequestDto> todoLists) {
        try {
            for (CreateTodoListRequestDto t : todoLists) {
                String nickname = t.getNickname();
                String categoryName = t.getCategoryName();
                String todoListTitle = t.getTodoListTitle();
                LocalDate startDate = t.getStartDate();
                LocalDate endDate = t.getEndDate();
                todoListsService.createTodoList(nickname, categoryName, todoListTitle, startDate, endDate);
            }
            return utils.getSuccessResponse("정상적으로 투두리스트를 추가했습니다.", null);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }

    // 투두리스트 업데이트
    @PatchMapping("/updateTodoList")
    public ResponseEntity<?> updateTodoListTitle(@RequestBody UpdateTodoListRequestDto updateDto) {
        try {
            String nickname = updateDto.getNickname();
            String todoListTitle = updateDto.getTodoListTitle();
            LocalDate startDate = updateDto.getStartDate();
            Element element = updateDto.getElement();
            String newElement = updateDto.getNewElement();

            todoListsService.updateTodoList(nickname, todoListTitle, startDate, element, newElement);

            return utils.getSuccessResponse("정상적으로 투두리스트를 업데이트했습니다", null);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }

    // 투두리스트 조회
    @GetMapping("/get")
    public ResponseEntity<?> getTodoLists(@RequestParam String nickname, @RequestParam LocalDate date) {
        try {
            List<TodoListsResponseDto> todoLists = todoListsService.getTodoLists(nickname, date);
            return utils.getSuccessResponse("정상적으로 투두리스트를 조회했습니다.", todoLists);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }

    // 투두리스트 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTodoList(@RequestParam String nickname,
                                             @RequestParam String todoListTitle, @RequestParam LocalDate startDate) {
        try {
            todoListsService.deleteTodoList(nickname, todoListTitle, startDate);
            return utils.getSuccessResponse("정상적으로 투두리스트를 삭제했습니다", null);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }
}
