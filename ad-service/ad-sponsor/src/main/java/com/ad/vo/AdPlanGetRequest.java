package com.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * created by Mr.F on 2019/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {
    //可以有多个id来看是否创建成功
    private List<Long> ids;
    private Long userId;

    public boolean validate() {
        return userId != null && !CollectionUtils.isEmpty(ids);

    }

}