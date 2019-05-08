package com.ad.entity;


import com.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * created by Mr.F on 2019/5/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit")
public class AdUnit {

    private Long id;

    private Long planId;

    private String unitName;

    private Integer unitStatus;

    /** 广告位类型(开屏, 贴片, 中贴...) */
    private Integer positionType;

    private Long budget;

    private Date createTime;

    private Date updateTime;

    public AdUnit(Long planId, String unitName,
                  Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
