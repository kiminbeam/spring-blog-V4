package com.example.blog2.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAll_test(){
        List<Board> boards = boardRepository.findAll();
        System.out.println();

        for (Board board : boards) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("===================");
        }
    }

    @Test
    public void saveTest(){
        //given
        String title = "제목6";
        String content = "내용6";

        //when
        boardRepository.save(title, content);

        //eye
        Board board = boardRepository.findById(6);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
        System.out.println(board.getCreatedAt());
    }

    @Test
    public void deleteTest(){
        //given
        int id = 1;

        //when
        boardRepository.delete(id);

        //then (eye)
        List<Board> BoardList = boardRepository.findAll();
        System.out.println("size:" + BoardList.size()) ;
    }

    @Test
    public void updateTest(){
        //given
        int id = 1;

        String title = "제목1 수정본";
        String content = "내용1 수정본";

        //when
        boardRepository.update(id,title,content);

        //then || eye
        Board board = boardRepository.findById(id);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
        System.out.println(board.getCreatedAt());
    }

    @Test
    public void findByIdTest(){
        int id = 5;
        Board board = boardRepository.findById(id);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
        System.out.println(board.getCreatedAt());
    }
}// 테스트 종료시 롤백한다. (@Transactional)
