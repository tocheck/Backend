package com.example.yeminhye_project.exception;

public class HexCodeUpdateFailedException extends RuntimeException {
    public HexCodeUpdateFailedException(String categoryName, String newHexCode, Throwable cause) {
      super(categoryName + "카테고리의 색상을 " + newHexCode + "로 변경하는 데 실패했습니다.", cause);
    }
}
