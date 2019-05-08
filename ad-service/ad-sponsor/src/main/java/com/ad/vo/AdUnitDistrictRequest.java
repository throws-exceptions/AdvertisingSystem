package com.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by Mr.F on 2019/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistricts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitDistrict {

        private Long unitId;
        private String province;
        private String city;
    }
}
