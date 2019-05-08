package com.ad.dao;


import com.ad.entity.Creative;
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
public interface CreativeDao {
    String TABLE_NAME=" ad_user ";
    String INSERT_FIELDS=" name,type,material_type,height,width,size,duration,audit_status," +
            "user_id,url,create_time,update_time ";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Insert({"insert",TABLE_NAME,"(",INSERT_FIELDS,") values (name=#{name},type=#{type},material_type=#{materialType}" +
            ",height=#{height},width=#{width),size=#{size},duration=#{duration},audit_status=#{audit_status}," +
            "user_id=#{user_id},url=#{url},create_time=#{create_time},update_time=#{update_time}"})
    int insertCreative(Creative creative);
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where user_id=#{userId}"})
    Creative selectCreativeByUserId(Long userId);
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    Creative selectById(@Param("id")Long id);
    default List<Creative> selectAllById(List<Long> ids){
        List<Creative> creatives=new ArrayList<>();
        for (Long id:ids) {
            creatives.add(selectById(id));
        }
        return creatives;
    }
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME})
    List<Creative> selectAll();
}
