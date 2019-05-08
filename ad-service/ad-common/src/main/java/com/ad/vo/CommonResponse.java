package com.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * created by Mr.F on 2019/4/30
 */
@Data //对各个属性实现set和get方法
@NoArgsConstructor //无参构造函数
@AllArgsConstructor //全参数构造函数
public class CommonResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    public CommonResponse(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
