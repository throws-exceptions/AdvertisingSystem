package com.ad.index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * created by Mr.F on 2019/5/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanObject {
    private Long planId;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;
    public void updateAdPlanObject(AdPlanObject adPlanObject){
        if(adPlanObject.getPlanId()!=null){
            this.planId=adPlanObject.getPlanId();
        }
        if(adPlanObject.getUserId()!=null){
            this.userId=adPlanObject.getUserId();
        }
        if(adPlanObject.getPlanStatus()!=null){
            this.planStatus=adPlanObject.getPlanStatus();
        }
        if(adPlanObject.getStartDate()!=null){
            this.startDate=adPlanObject.getStartDate();
        }
        if(adPlanObject.getEndDate()!=null){
            this.endDate=adPlanObject.getEndDate();
        }
    }
}
