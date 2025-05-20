package com.example.yeminhye_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "todo_lists")
@Getter
@Setter
@ToString(exclude = {"users", "categories"})
public class TodoLists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name="todo_list_id", nullable = false)
    private Long todoListId;

    @Column(name="todo_list_title", nullable = false)
    private String todoListTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nickname")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories categories;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private Status status = Status.NOT_STARTED;
}
