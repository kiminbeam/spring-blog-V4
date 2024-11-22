package com.example.blog2._core.error;

import com.example.blog2._core.error.ex.Exception400;
import com.example.blog2._core.error.ex.Exception404;
import com.example.blog2.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 에러처리
@RestControllerAdvice
public class MyControllerAdvice {

//    @ExceptionHandler(Exception400.class)
//    public String err400(Exception400 e){
//        System.out.println("err400");
//
//        String body = """
//                <script>
//                    alert('${msg}');
//                    history.back();
//                </script>
//                """.replace("${msg}",e.getMessage());
//        return body;
//    }

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> err400(Exception400 e){
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        return rn;
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> err404(Exception404 e){
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND);
        return rn;
    }
}
