package com.example.blog2.board;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardResponse {


    @Data
    public static class DetailDTO{
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();

            LocalDateTime localDateTime = board.getCreatedAt().toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            this.createdAt = localDateTime.format(formatter);
            //this.createdAt = board.getCreatedAt().toString(); // TODO: 2024.11.18 형태로 변경하기
        }
    }


    @Data
    public static class DTO{
        private int id;
        private String title;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }


}
