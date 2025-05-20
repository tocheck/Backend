package com.example.yeminhye_project.service;

import com.example.yeminhye_project.dto.CategoryResponseDto;
import com.example.yeminhye_project.entity.Categories;
import com.example.yeminhye_project.entity.Users;
import com.example.yeminhye_project.exception.*;
import com.example.yeminhye_project.repository.CategoriesRepository;
import com.example.yeminhye_project.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final UsersRepository usersRepository;

    private final ServiceUtils utils;

    // 카테고리 추가하는 서비스 메서드
    public void createCategory(String nickname, String categoryName, String hexCode) {
        Users userEntity = utils.getUserEntity(nickname);

        // 카테고리가 중복되는 경우 예외 처리
        if (categoriesRepository.existsByUsersAndCategoryName(userEntity, categoryName))
            throw new DuplicateCategoryException(categoryName);

        // 정상 흐름
        try {
            Categories categoryEntity = new Categories();
            categoryEntity.setUsers(userEntity);
            categoryEntity.setCategoryName(categoryName);
            categoryEntity.setHexCode(hexCode);
            categoriesRepository.save(categoryEntity);
        }
        catch (Exception e) { throw new CategorySaveFailedException(categoryName, e); }
    }

    //
    public void updateCategoryName(String nickname, String categoryName, String newCategoryName) {
        Users userEntity = utils.getUserEntity(nickname);

        // 예외 처리
        if (!categoriesRepository.existsByUsersAndCategoryName(userEntity, categoryName))
            throw new CategoryNotFoundException(categoryName);
        if (categoriesRepository.existsByUsersAndCategoryName(userEntity, newCategoryName))
            throw new DuplicateCategoryException(newCategoryName);

        // 정상 흐름
        try { categoriesRepository.updateCategoryNameByUsersAndCategoryName(userEntity, categoryName, newCategoryName); }
        catch (Exception e) { throw new CategoryNameUpdateFailedException(categoryName, e); }
    }

    public void updateHexCode(String nickname, String categoryName, String newHexCode) {
        Users userEntity = utils.getUserEntity(nickname);

        if (!categoriesRepository.existsByUsersAndCategoryName(userEntity, categoryName))
            throw new CategoryNotFoundException(categoryName);

        try{ categoriesRepository.updateHexCodeByUsersAndCategoryNameAndHexCode(userEntity, categoryName, newHexCode); }
        catch (Exception e) { throw new HexCodeUpdateFailedException(categoryName, newHexCode, e); }
    }

    // 해당 사용자가 생성한 카테고리를 전부 조회하는 서비스 메서드
    public List<CategoryResponseDto> getCategories(String nickname) {
        Users userEntity = utils.getUserEntity(nickname);

        // 정상 흐름
        try {
            List<CategoryResponseDto> result = new ArrayList<>();
            List<Categories> categoriesList = categoriesRepository.findByUsers(userEntity);
            for (Categories c : categoriesList)
                result.add(new CategoryResponseDto(c.getCategoryName()));

            return result;
        }
        catch (Exception e) { throw new CategoryFindFailedException(nickname, e); }
    }

    // 카테고리 삭제하는 서비스 메서드
    public void deleteCategory(String nickname, String categoryName) {
        Users userEntity = utils.getUserEntity(nickname);

        // 해당 카테고리가 없는 경우 예외 처리
        if (!categoriesRepository.existsByUsersAndCategoryName(userEntity, categoryName))
            throw new CategoryNotFoundException(categoryName);

        // 정상 흐름
        try { categoriesRepository.deleteByUsersAndCategoryName(userEntity, categoryName); }
        catch (Exception e) { throw new CategoryDeleteFailedException(categoryName, e); }
    }
}
