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
public class AdUnitKeywordRequest {

    private List<UnitKeyword> unitKeywords;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitKeyword {

        private Long unitId;
        private String keyword;
    }
}
