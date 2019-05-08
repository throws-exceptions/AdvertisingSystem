package com.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * created by Mr.F on 2019/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanRequest {
    //主要用在创建推广计划和更新推广计划和删除推广计划
    private Long id;
    private Long userId;
    private String planName;
    private String startDate;
    private String endDate;
    public boolean createValidate(){
        return userId!=null
                &&!StringUtils.isEmpty(planName)
                &&!StringUtils.isEmpty(startDate)
                &&!StringUtils.isEmpty(endDate);

    }
    public boolean updateValidate(){
        return id!=null && userId!=null;
    }
    public boolean deleteValidate(){
        return id!=null && userId!=null;
    }
}
