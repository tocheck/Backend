package com.example.yeminhye_project.exception;

public class CategoryNameUpdateFailedException extends RuntimeException {
    public CategoryNameUpdateFailedException(String categoryName, Throwable cause) { super("해당 카테고리명 업데이트에 실패했습니다: " + categoryName, cause);}
}
