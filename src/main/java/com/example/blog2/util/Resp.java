package com.example.blog2.util;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resp<T> {
    private boolean success; // true = 통신성공 false=
    private String msg;
    private T body;

    //메서드 실행 때 리턴타입 정하고 싶을때
    public static <T> Resp<T> ok(T body) {
        return new Resp<>(true, "성공", body);
    }

    public static <T> Resp<T> fail(String msg) {
        return new Resp<>(false, msg, null);
    }

}
