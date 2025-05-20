package com.example.yeminhye_project.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String nickname) {
        super("해당 사용자가 존재하지 않습니다: " + nickname);
    }
}
