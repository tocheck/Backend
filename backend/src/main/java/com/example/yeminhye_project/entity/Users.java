package com.example.yeminhye_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class Users {
    @Id
    @Column(name="nickname")
    private String nickname;

    @Column(name="kakao_nickname")
    private String kakaoNickname;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoLists> todoLists = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categories> categories = new ArrayList<>();
}