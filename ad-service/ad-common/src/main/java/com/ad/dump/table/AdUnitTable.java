package com.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Mr.F on 2019/5/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitTable {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;

    private Long planId;
}
