package com.example.yeminhye_project.repository;

import com.example.yeminhye_project.entity.Categories;
import com.example.yeminhye_project.entity.Status;
import com.example.yeminhye_project.entity.TodoLists;
import com.example.yeminhye_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoListsRepository extends JpaRepository<TodoLists, Long> {
    // 해당 사용자 + 카테고리 + 시작일의 투두리스트 존재 여부 반환
    boolean existsByUsersAndTodoListTitleAndStartDate(Users userEntity, String todoListTitle, LocalDate startDate);

    // 카테고리 업데이트
    @Modifying
    @Query("UPDATE TodoLists t " +
            "SET t.categories=:newCategoryEntity " +
            "WHERE t.users=:userEntity AND t.todoListTitle=:todoListTitle AND t.startDate=:startDate ")
    void updateCategory(@Param("userEntity") Users userEntity,
                      @Param("todoListTitle") String todoListTitle,
                      @Param("startDate") LocalDate startDate,
                      @Param("newCategory") Categories newCategoryEntity);

    // 투두리스트 제목 업데이트
    @Modifying
    @Query("UPDATE TodoLists t " +
            "SET t.todoListTitle=:newTodoListTitle " +
            "WHERE t.users=:userEntity AND t.todoListTitle=:todoListTitle AND t.startDate=:startDate ")
    void updateTodoListTitle(@Param("userEntity") Users userEntity,
                             @Param("todoListTitle") String todoListTitle,
                             @Param("startDate") LocalDate startDate,
                             @Param("newTodoListTitle") String newTodoListTitle);

    // 상태 업데이트
    @Modifying
    @Query("UPDATE TodoLists t " +
            "SET t.status=:newStatus " +
            "WHERE t.users=:userEntity AND t.todoListTitle=:todoListTitle AND t.startDate=:startDate ")
    void updateStatus(@Param("userEntity") Users userEntity,
                      @Param("todoListTitle") String todoListTitle,
                      @Param("startDate") LocalDate startDate,
                      @Param("newStatus") Status newStatus);

    // 시작일 업데이트
    @Modifying
    @Query("UPDATE TodoLists t " +
            "SET t.startDate=:newStartDate " +
            "WHERE t.users=:userEntity AND t.todoListTitle=:todoListTitle AND t.startDate=:startDate ")
    void updateStartDate(@Param("userEntity") Users userEntity,
                         @Param("todoListTitle") String todoListTitle,
                         @Param("startDate") LocalDate startDate,
                         @Param("newStartDate") LocalDate newStartDate);

    // 마감일 업데이트
    @Modifying
    @Query("UPDATE TodoLists t " +
            "SET t.endDate=:newEndDate " +
            "WHERE t.users=:userEntity AND t.todoListTitle=:todoListTitle AND t.startDate=:startDate ")
    void updateEndDate(@Param("userEntity") Users userEntity,
                       @Param("todoListTitle") String todoListTitle,
                       @Param("startDate") LocalDate startDate,
                       @Param("newEndDate") LocalDate newEndDate);

    // 투두리스트 조회
    @Query("SELECT t " +
            "FROM TodoLists t " +
            "WHERE t.users=:userEntity AND t.startDate<=:date AND t.endDate>=:date")
    List<TodoLists> findByUsersAndDate(@Param("userEntity") Users userEntity,
                                       @Param("date") LocalDate date);

    // 투두리스트 삭제
    void deleteByUsersAndTodoListTitleAndStartDate(Users userEntity, String todoListTitle, LocalDate startDate);
}
