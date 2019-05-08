package com.ad.dao;

import com.ad.entity.AdPlan;
import com.ad.entity.AdUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by Mr.F on 2019/5/1
 */
@Component
@Mapper
public interface AdPlanDao {
    String TABLE_NAME=" ad_plan ";
    String INSERT_FIELDS=" user_id,plan_name,plan_status,start_date,end_date,create_time,update_time ";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id} and user_id=#{userId}"})
    AdPlan selectByIdAndUserId(@Param("id")Long id,@Param("userId")Long userId);

    default List<AdPlan> selectAllByIdAndUserId(List<Long> ids, Long userId){
        List<AdPlan> list=new ArrayList<AdPlan>() ;
        for (Long id:ids) {
            list.add(selectByIdAndUserId(id,userId));
        }
        return list;
    }
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where user_id=#{userId} and plan_name=#{planName}"})
    AdPlan selectByUserIdAndPlanName(@Param("userId")Long userId,@Param("planName")String planName);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where user_id=#{userId}"})
    AdPlan selectByUserId(Long userId);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    AdPlan selectById(Long id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where plan_status=#{planStatus}"})
    List<AdPlan> selectAllByPlanStatus(@Param("planStatus")Integer planStatus);
    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{planName},#{planStatus},#{startDate},#{endDate},#{createTime},#{updateTime})"})
    int insertPlan(AdPlan adPlan);
    @Update({"update",TABLE_NAME,"set update_time=#{updateTime},plan_name=#{planName}," +
            "start_date=#{startDate},end_date=#{endDate} where id=#{id}"})
    void updatePlan(AdPlan adPlan);
}
