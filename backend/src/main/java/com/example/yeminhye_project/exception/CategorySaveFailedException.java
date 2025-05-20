package com.example.yeminhye_project.exception;

public class CategorySaveFailedException extends RuntimeException {
    public CategorySaveFailedException(String categoryName, Throwable cause) { super("해당 카테고리를 추가하지 못했습니다: " + categoryName, cause); }
}
