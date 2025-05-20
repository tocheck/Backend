package com.example.yeminhye_project.service;

import com.example.yeminhye_project.entity.Categories;
import com.example.yeminhye_project.entity.Users;
import com.example.yeminhye_project.exception.CategoryNotFoundException;
import com.example.yeminhye_project.exception.UserNotFoundException;
import com.example.yeminhye_project.repository.CategoriesRepository;
import com.example.yeminhye_project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceUtils {
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;

    public Users getUserEntity(String nickname) {
        return usersRepository.findById(nickname)
                .orElseThrow(() -> new UserNotFoundException(nickname));
    }

    public Categories getCategoryEntity(String categoryName) {
        return categoriesRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(categoryName));
    }
}
