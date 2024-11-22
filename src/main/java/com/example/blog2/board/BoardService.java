package com.example.blog2.board;

import com.example.blog2._core.error.ex.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse.DTO> 게시글목록보기 (){

        return boardRepository.findAll().stream()
                .map(BoardResponse.DTO::new)
                .toList();
    }


    public BoardResponse.DetailDTO 게시글상세보기(int id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 아이디의 게시글이 없습니다.:" + id));

        return new BoardResponse.DetailDTO(board);
    }


    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDto saveDto) {
        boardRepository.save(saveDto.toEntity());
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    }// commit OR rollback

    @Transactional
    public void 게시글수정하기(int id,BoardRequest.UpdateDto updateDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 아이디의 게시글이 없습니다.:" + id));
        board.update(updateDto.getTitle(), updateDto.getContent());
    }// 영속화 된 객체상태 변경 - update + commit => 더티체킹
}
