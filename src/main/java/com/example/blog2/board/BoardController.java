package com.example.blog2.board;

import com.example.blog2._core.error.ex.Exception400;
import com.example.blog2.util.Resp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api")
    public Resp<?> list() {
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        return Resp.ok(boardList);
    }

    // x-www는 클래스로 받을 수 있다. // json은 앞에@RequestBody 추가
    @PostMapping("/api/board")
    public Resp<?> save(@Valid @RequestBody BoardRequest.SaveDto saveDTO, Errors errors) {

        boardService.게시글쓰기(saveDTO);

        return Resp.ok(null);
    }


    /**
     * 쿼리스트링(where 절) : /board?title=바다
     * 패스변수 (where 절) : /board/1
     * 유니크로 구분 유니크 O = 패스변수 || 유니크 X = 쿼리스트링
     * 주소설계 규칙
     */
    // 상세보기 + 업데이트에 사용
    @GetMapping("/api/board/{id}")
    public Resp<?> Detail(@PathVariable("id") int id) {
        //서비스 요청
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);


        return Resp.ok(boardDetail);
    }

    @DeleteMapping("/api/board/{id}")
    public Resp<?> delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }


    @PutMapping("/api/board/{id}")
    public Resp<?> update(@PathVariable("id") int id,@Valid @RequestBody BoardRequest.UpdateDto updateDto, Errors errors , Model model){

        boardService.게시글수정하기(id,updateDto);

        return Resp.ok(null);
    }


    public String exLongString() {

        String id = "username1";

        String data = "안녕하세요. 저는 ${username} 입니다. 앞으로 잘 부탁드립니다.".replace("username", id);

        return data;
    }
}
