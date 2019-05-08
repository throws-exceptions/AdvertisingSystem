package com.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Mr.F on 2019/5/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanResponse {
    private Long id;
    private String planName;
    //给出对应的id和planName代表创建是否成功
}
