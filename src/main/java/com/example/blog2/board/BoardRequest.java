package com.example.blog2.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

public class BoardRequest {
    
    //DTO에서 유효성 검사
    //변수 추가 편함


    @Data // getter, setter, toString 생성
    public static class SaveDto {
        @NotBlank
        private String title;
        @NotBlank
        private String content;

        public Board toEntity(){
            Board board = new Board(null, title, content, null);
            return board;
        }
    }

    @Data
    public static class UpdateDto { 
        //private String id;
        @NotBlank // 밸리데이션에서 오류
        private String title;
        @NotBlank
        private String content;
    }
}
