package com.ad.dao.unit_condition;



import com.ad.entity.unit_condition.AdUnitIt;
import com.ad.exception.AdException;
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
public interface AdUnitItDao{
    String TABLE_NAME=" ad_unit_it ";
    String INSERT_FIELDS=" unit_id,it_tag ";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (unit_id=#{unitId},it_tag=#{itTag})"})
    int insertUnitIt(AdUnitIt adUnitIt);
    default List<AdUnitIt> insertAndSelectAllUnitIt(List<AdUnitIt> unitIts){
        List<AdUnitIt> adUnitIts=new ArrayList<>();
        for (AdUnitIt adUnitIt:unitIts) {
            insertUnitIt(adUnitIt);
            adUnitIts.add(adUnitIt);
        }
        return adUnitIts;
    }
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME})
    List<AdUnitIt> selectAll();
}
