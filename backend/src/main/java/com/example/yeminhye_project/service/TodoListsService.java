package com.example.yeminhye_project.service;

import com.example.yeminhye_project.dto.TodoListsResponseDto;
import com.example.yeminhye_project.dto.UpdateTodoListRequestDto;
import com.example.yeminhye_project.entity.*;
import com.example.yeminhye_project.exception.TodoListSaveFailedException;
import com.example.yeminhye_project.repository.TodoListsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListsService {
    private final ServiceUtils utils;
    private final TodoListsRepository todoListsRepository;

    // 투두리스트 추가
    public void createTodoList(String nickname, String categoryName, String todoListTitle, LocalDate startDate, LocalDate endDate) {
        Users userEntity = utils.getUserEntity(nickname);
        Categories categoryEntity = utils.getCategoryEntity(categoryName);

        try {
            TodoLists todoListEntity = new TodoLists();
            todoListEntity.setUsers(userEntity);
            todoListEntity.setCategories(categoryEntity);
            todoListEntity.setTodoListTitle(todoListTitle);
            todoListEntity.setStartDate(startDate);
            todoListEntity.setEndDate(endDate);
            todoListEntity.setStatus(Status.NOT_STARTED);

            todoListsRepository.save(todoListEntity);
        }
        catch (Exception e) { throw new TodoListSaveFailedException(todoListTitle, e); }
    }

    // 투두리스트 업데이트
    @Transactional
    public void updateTodoList(String nickname, String todoListTitle, LocalDate startDate, Element element, String newElement) {
        Users userEntity = utils.getUserEntity(nickname);

        if (!todoListsRepository.existsByUsersAndTodoListTitleAndStartDate(userEntity, todoListTitle, startDate)) { return; }

        // 투두리스트 제목 업데이트
        if (element.equals(Element.todoListTitle))
            todoListsRepository.updateTodoListTitle(userEntity, todoListTitle, startDate, newElement);

        // 투두리스트 카테고리 업데이트
        else if (element.equals(Element.category)) {
            Categories newCategoryEntity = utils.getCategoryEntity(newElement);
            todoListsRepository.updateCategory(userEntity, todoListTitle, startDate, newCategoryEntity);
        }

        // 투두리스트 상태 업데이트
        else if (element.equals(Element.status)) {
            Status newStatus = Status.valueOf(newElement);
            todoListsRepository.updateStatus(userEntity, todoListTitle, startDate, newStatus);
        }

        // 투두리스트 시작일 업데이트
        else if (element.equals(Element.startDate)) {
            LocalDate newDate = LocalDate.parse(newElement);
            todoListsRepository.updateStartDate(userEntity, todoListTitle, startDate, newDate);
        }

        // 투두리스트 마감일 업데이트
        else if (element.equals(Element.endDate)) {
            LocalDate newDate = LocalDate.parse(newElement);
            todoListsRepository.updateEndDate(userEntity, todoListTitle, startDate, newDate);
        }
    }

    // 투두리스트 조회
    public List<TodoListsResponseDto> getTodoLists(String nickname, LocalDate date) {
        Users userEntity = utils.getUserEntity(nickname);
        List<TodoListsResponseDto> result = new ArrayList<>();

        List<TodoLists> todoLists = todoListsRepository.findByUsersAndDate(userEntity, date);

        for (TodoLists t : todoLists) {
            Categories categoryEntity = t.getCategories();
            String CategoryName = categoryEntity.getCategoryName();
            String hexCode = categoryEntity.getHexCode();
            String todoListTitle = t.getTodoListTitle();
            Status status = t.getStatus();
            LocalDate startDate = t.getStartDate();
            LocalDate endDate = t.getEndDate();

            result.add(new TodoListsResponseDto(CategoryName, hexCode, todoListTitle, status, startDate, endDate));
        }

        return result;
    }

    public void deleteTodoList(String nickname, String todoListTitle, LocalDate startDate) {
        Users userEntity = utils.getUserEntity(nickname);
        todoListsRepository.deleteByUsersAndTodoListTitleAndStartDate(userEntity, todoListTitle, startDate);
    }
}
