package com.example.yeminhye_project.exception;

public class DuplicateCategoryException extends RuntimeException {
    public DuplicateCategoryException(String categoryName) { super("이미 존재하는 카테고리입니다: " + categoryName); }
}
