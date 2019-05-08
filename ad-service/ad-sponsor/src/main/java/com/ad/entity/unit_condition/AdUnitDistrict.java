package com.ad.entity.unit_condition;

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

/**
 * created by Mr.F on 2019/5/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrict {

    private Long id;

    private Long unitId;

    private String province;

    private String city;

    public AdUnitDistrict(Long unitId, String province, String city) {
        this.unitId = unitId;
        this.province = province;
        this.city = city;
    }
}
