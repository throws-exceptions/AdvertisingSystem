package com.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * created by Mr.F on 2019/5/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private Long id;

    private String username;

    private String token;

    private Date createTime;

    private Date updateTime;

}
