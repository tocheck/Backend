package com.example.yeminhye_project.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String categoryName) { super("해당 카테고리가 존재하지 않습니다: " + categoryName); }
}
