package com.example.yeminhye_project.exception;

public class CategoryDeleteFailedException extends RuntimeException{
    public CategoryDeleteFailedException(String categoryName, Throwable cause) {
        super("해당 카테고리를 삭제하는 데 실패했습니다: " + categoryName, cause);
    }
}
