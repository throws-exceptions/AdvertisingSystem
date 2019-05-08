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
public class CreativeUnitRequest {

    private List<CreativeUnitItem> unitItems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreativeUnitItem {

        private Long creativeId;
        private Long unitId;
    }
}
