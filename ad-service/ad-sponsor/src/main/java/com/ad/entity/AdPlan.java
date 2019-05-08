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
public class AdPlan {

    private Long id;

    private Long userId;

    private String planName;

    private Integer planStatus;

    private Date startDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;

    public AdPlan(Long userId,String planName,Date startDate,Date endDate){
        this.userId=userId;
        this.planName=planName;
        this.planStatus=CommonStatus.VALID.getStatus();
        this.startDate=startDate;
        this.endDate=endDate;
        this.createTime=new Date();
        this.updateTime=this.createTime;
    }
}
