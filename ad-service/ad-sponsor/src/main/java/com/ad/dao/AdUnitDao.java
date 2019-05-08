package com.ad.dao;

import com.ad.entity.AdUnit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mr.F on 2019/5/1
 */
@Component
@Mapper
public interface AdUnitDao {
    String TABLE_NAME=" ad_unit ";
    String INSERT_FIELDS=" plan_id,unit_name,unit_status,position_type,budget,create_time,update_time ";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where plan_id=#{planId} and unit_name=#{unitName}"})
    AdUnit selectByPlanIdAndUnitName(@Param("planId")Long planId, @Param("unitName")String unitName);
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where unit_status=#{unitStatus}"})
    List<AdUnit> selectByUnitStatus(Integer unitStatus);
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (plan_id=#{planId},unit_name=#{unitName},unit_status=#{unitStatus}," +
            "position_type=#{positionType},budget=#{budget},create_time=#{createTime},update_time=#{updateTime}"})
    int insertUnit(AdUnit adUnit);
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    AdUnit selectById(@Param("id")Long id);
    default List<AdUnit> selectAllById(List<Long> ids){
        List<AdUnit> adUnits=new ArrayList<>();
        for (Long id:ids) {
            adUnits.add(selectById(id));
        }
        return adUnits;
    }
}
