package com.ad.entity;

import com.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * created by Mr.F on 2019/5/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUser {
    private Long id;

    private String username;

    private String token;

    private Integer userStatus;

    private Date createTime;

    private Date updateTime;

    public AdUser(String username,String token){
        this.username=username;
        this.token=token;
        this.userStatus=CommonStatus.VALID.getStatus();
        this.createTime=new Date();
        this.updateTime=this.createTime;
    }

}
