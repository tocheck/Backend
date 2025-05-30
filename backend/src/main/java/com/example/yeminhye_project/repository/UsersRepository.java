package com.example.yeminhye_project.repository;

import com.example.yeminhye_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    // 기본적인 CRUD 기능 제공.
}
