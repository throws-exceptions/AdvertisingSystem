package com.ad.constant;

import lombok.Getter;

/**
 * created by Mr.F on 2019/5/1
 */
@Getter
public enum CommonStatus {

    VALID(1,"有效状态"),
    INVALID(0,"无效状态");
    private Integer status;
    private String desc;
    CommonStatus(Integer status,String desc){
        this.status=status;
        this.desc=desc;
    }
}
