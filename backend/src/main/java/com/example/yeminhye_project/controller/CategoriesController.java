package com.example.yeminhye_project.controller;

import com.example.yeminhye_project.dto.*;
import com.example.yeminhye_project.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final ControllerUtils utils;

    // 카테고리 추가 api
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequestDto categoryDto) {
        try {
            String nickname = categoryDto.getNickname();
            String categoryName = categoryDto.getCategoryName();
            String hexCode = categoryDto.getHexCode();
            categoriesService.createCategory(nickname, categoryName, hexCode);

            return utils.getSuccessResponse("정상적으로 카테고리를 추가했습니다", null);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }

    // 카테고리 이름 수정 api
    @PatchMapping("/updateCategoryName")
    public ResponseEntity<?> updateCategoryName(@RequestBody UpdateCategoryNameRequestDto categoryNameDto) {
        try {
            String nickname = categoryNameDto.getNickname();
            String categoryName = categoryNameDto.getCategoryName();
            String newCategoryName = categoryNameDto.getNewCategoryName();

            categoriesService.updateCategoryName(nickname, categoryName, newCategoryName);

            return utils.getSuccessResponse("정상적으로 카테고리 이름을 수정했습니다: " + categoryNameDto + " -> " + newCategoryName, null);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }

    // 카테고리 색상 수정 api
    @PatchMapping("/updateHexCode")
    public ResponseEntity<?> updateHexCode(@RequestBody UpdateHexCodeRequestDto hexCodeRequestDto) {
        try {
            String nickname = hexCodeRequestDto.getNickname();
            String categoryName = hexCodeRequestDto.getCategoryName();
            String newHexCode = hexCodeRequestDto.getNewHexCode();

            categoriesService.updateHexCode(nickname, categoryName, newHexCode);

            return utils.getSuccessResponse("정상적으로 " + categoryName + " 카테고리 색상을 수정했습니다", null);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }

    // 카테고리 불러오는 api
    @GetMapping("/get")
    public ResponseEntity<?> getCategory(@RequestParam String nickname) {
        try {
            List<CategoryResponseDto> categoriesList = categoriesService.getCategories(nickname);

            if (categoriesList == null)
                return utils.getSuccessResponse("먼저 카테고리를 추가해주세요.", null);
            return utils.getSuccessResponse("정상적으로 카테고리를 조회했습니다.", categoriesList);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }

    // 카테고리 삭제하는 api
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam String nickname, @RequestParam String categoryName) {
        try {
            categoriesService.deleteCategory(nickname, categoryName);
            return utils.getSuccessResponse("정상적으로 카테고리를 삭제했습니다.", null);
        }
        catch (Exception e) { return utils.assertBySystem(e); }
    }
}
