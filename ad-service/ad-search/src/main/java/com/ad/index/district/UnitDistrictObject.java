package com.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Mr.F on 2019/5/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDistrictObject {

    private Long unitId;
    private String province;
    private String city;

    // <String, Set<Long>>
    // province-city
}
