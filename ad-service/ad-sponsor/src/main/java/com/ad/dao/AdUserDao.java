package com.ad.dao;

import com.ad.entity.AdUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * created by Mr.F on 2019/5/1
 */
@Component
@Mapper
public interface AdUserDao {
    String TABLE_NAME=" ad_user ";
    String INSERT_FIELDS=" user_name,token,user_status,create_time,update_time";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where user_name=#{userName}"})
    AdUser selectByUserName(String userName);
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    AdUser selectById(Long id);
    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userName},#{token},#{createTime},#{updateTime})"})
    int insertUser(AdUser adUser);
}
