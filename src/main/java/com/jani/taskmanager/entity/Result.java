package com.jani.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

/**
 * @author Jani
 * @data 2026/11/22 21:26
 **/
@Data
@NoArgsConstructor // 生成无参构造
@AllArgsConstructor // 生成全参构造
public class Result<T> {
    private int code;
    private String message;
    private T data;

    // 成功
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.code = 200;
        r.message = "操作成功";
        r.data = data;
        return r;
    }

    // 失败
    public static <T> Result<T> fail(int code, String msg) {
        Result<T> r = new Result<>();
        r.code = code;
        r.message = msg;
        r.data = null;
        return r;
    }

}
