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
public class AdCreativeTable {

    private Long adId;
    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;
}
