package com.example.yeminhye_project.exception;

public class TodoListSaveFailedException extends RuntimeException{
    public TodoListSaveFailedException(String todoListTitle, Throwable cause) {
        super("해당 투두리스트 저장에 실패했습니다: " + todoListTitle, cause);
    }
}
