package com.example.yeminhye_project.exception;

public class NoCategoryRegisteredException extends RuntimeException {
    public NoCategoryRegisteredException(String nickname) { super("해당 사용자가 추가한 카테고리가 없습니다: " + nickname); }
}
