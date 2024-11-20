package com.example.blog2.board;

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

    public BoardResponse.UpdateFormDTO 게시글수정화면보기(int id) {

        Board board = boardRepository.findById(id);

        return new BoardResponse.UpdateFormDTO(board);
    }

    public BoardResponse.DetailDTO 게시글상세보기(int id) {

        Board board = boardRepository.findById(id);

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
        //String id = updateDto.getId();
        //int idnum = Integer.parseInt(id);

        boardRepository.update(id, updateDto.getTitle(), updateDto.getContent());
    }
}
