package com.ad.dao.unit_condition;


import com.ad.entity.unit_condition.CreativeUnit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mr.F on 2019/5/1
 */
@Component
@Mapper
public interface CreativeUnitDao{
    String TABLE_NAME=" ad_unit_it ";
    String INSERT_FIELDS=" unit_id,creative_id ";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (unit_id=#{unitId},creative_id=#{creative_id})"})
    int insertUnitIt(CreativeUnit creativeUnit);
    default List<CreativeUnit> insertAndSelectAllCreativeUnit(List<CreativeUnit> Units){
        List<CreativeUnit> creativeUnits=new ArrayList<>();
        for (CreativeUnit creativeUnit:Units) {
            insertUnitIt(creativeUnit);
            creativeUnits.add(creativeUnit);
        }
        return creativeUnits;
    }
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME})
    List<CreativeUnit> selectAll();
}
