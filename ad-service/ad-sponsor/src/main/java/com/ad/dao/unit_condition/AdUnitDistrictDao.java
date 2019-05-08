package com.ad.dao.unit_condition;


import com.ad.entity.unit_condition.AdUnitDistrict;
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
public interface AdUnitDistrictDao {
    String TABLE_NAME=" ad_unit_it ";
    String INSERT_FIELDS=" unit_id,province,city ";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (province=#{province},city=#{city})"})
    int insertUnitIt(AdUnitDistrict adUnitIt);
    default List<AdUnitDistrict> insertAndSelectAllUnitDistrict(List<AdUnitDistrict> unitDistricts){
        List<AdUnitDistrict> adUnitDistricts=new ArrayList<>();
        for (AdUnitDistrict adUnitDistrict:unitDistricts) {
            insertUnitIt(adUnitDistrict);
            adUnitDistricts.add(adUnitDistrict);
        }
        return adUnitDistricts;
    }
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME})
    List<AdUnitDistrict> selectAll();
}
