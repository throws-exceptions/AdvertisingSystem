package com.ad.advice;

import com.ad.annotation.IgnoreResponseAdvice;
import com.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * created by Mr.F on 2019/4/30
 */
@RestControllerAdvice  //表明对响应实现统一拦截
@SuppressWarnings("all")//下面有很多不用的参数，所以会有warning标识
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    //根据方法参数或者类判断是否该拦截
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if(returnType.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        if(returnType.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        //当类或方法不被IgnoreResponseAdvice这个注解所标识，那么就启动CommonResponse
        return true;
    }
    //在写入响应之前做一些操作，返回的时候用户拿到的就是CommonResponse
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        CommonResponse<Object> commonResponse=new CommonResponse<>(0,"");
        if(body==null){
            //如果返回对象body为空，那么就直接返回一个参数为0和“”的commonResponse
            return commonResponse;
        }else if(body instanceof CommonResponse){
            //如果body已经是commonResponse，那么直接返回即可
            commonResponse=(CommonResponse<Object>) body;
        }else{
            //如果都不是上面两种情况，那么就把body设置为commonResponse中的一个参数
            commonResponse.setData(body);
        }
        //返回最后的commonResponse
        return commonResponse;
    }
}
