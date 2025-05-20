package com.example.yeminhye_project.exception;

public class CategoryFindFailedException extends RuntimeException {
    public CategoryFindFailedException(String nickname, Throwable cause) {
        super("해당 사용자의 카테고리를 조회하는 데 실패했습니다: " + nickname, cause);
    }
}
