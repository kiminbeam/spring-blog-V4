package com.example.blog2.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "board_tb")
@NoArgsConstructor //DB에서 조회해서 가져온 RS를 디폴트 생성자 호출 new 에 넣어줌
@Getter
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    @CreationTimestamp
    private Timestamp createdAt;
}
