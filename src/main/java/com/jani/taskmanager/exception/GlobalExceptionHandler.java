package com.jani.taskmanager.exception;

import com.jani.taskmanager.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 这个注解代表它是全局异常处理中心
public class GlobalExceptionHandler {

    // 捕获刚刚报的那个参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 返回 HTTP 400 状态码
    public Result<?>  handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 获取第一个错误提示信息（如果有多个字段校验失败，这里只取第一个展示）
        String errorMessage = "参数校验失败";
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null) {
            errorMessage = fieldError.getDefaultMessage();
        }
        return Result.fail(400,errorMessage);
    }
}