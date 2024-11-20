package com.example.blog2.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String list(Model model) {
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();

        model.addAttribute("models", boardList);

        return "list";
    }

    /**
     * 쿼리스트링(where 절) : /board?title=바다
     * 패스변수 (where 절) : /board/1
     * 유니크로 구분 유니크 O = 패스변수 || 유니크 X = 쿼리스트링
     * 주소설계 규칙
     */
    @GetMapping("/board/{id}")
    public String Detail(@PathVariable("id") int id, Model model) {
        //서비스 요청
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        model.addAttribute("model", boardDetail);

        return "detail";
    }

    @GetMapping("board/save-form")
    public String saveForm() {
        return "save-form";
    }


    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDto saveDTO) throws IOException { // x-www는 클래스로 받을 수 있다. // json은 앞에@RequestBody 추가
        boardService.게시글쓰기(saveDTO);

        return "redirect:/";
    }



    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, Model model) {
        BoardResponse.UpdateFormDTO boardDetail = boardService.게시글수정화면보기(id);
        model.addAttribute("model", boardDetail);

        return "update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id,BoardRequest.UpdateDto updateDto, Model model){
        //System.out.println("id: " + updateDto.getId());

        boardService.게시글수정하기(id,updateDto);

        BoardResponse.DetailDTO detailDTO = boardService.게시글상세보기(id);
        model.addAttribute("model", detailDTO);

        return "redirect:/board/" + id;
    }


    public String exLongString() {

        String id = "username1";

        String data = "안녕하세요. 저는 ${username} 입니다. 앞으로 잘 부탁드립니다.".replace("username", id);

        return data;
    }
}
