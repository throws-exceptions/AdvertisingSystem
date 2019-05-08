package com.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * created by Mr.F on 2019/5/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String username;
    public boolean validate(){
        //判断用户是否为空
        return !StringUtils.isEmpty(username);
    }
}
