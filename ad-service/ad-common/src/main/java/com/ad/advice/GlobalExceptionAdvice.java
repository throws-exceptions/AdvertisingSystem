package com.ad.advice;

import com.ad.exception.AdException;
import com.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * created by Mr.F on 2019/4/30
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    //当异常是AdException时，统一进来处理。可以定义多个异常，然后对应每个不同异常有不同的处理信息
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handleException(HttpServletRequest request,AdException adException){
        //处理异常时当做是一个响应，传入请求和异常,在响应中填入对应的参数，然后返回响应
        CommonResponse<String> response=new CommonResponse<>(-1,"系统出现异常");
        response.setData(adException.getMessage());
        return response;
    }
}
