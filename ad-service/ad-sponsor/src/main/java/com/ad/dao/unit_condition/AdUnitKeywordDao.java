package com.ad.dao.unit_condition;


import com.ad.entity.unit_condition.AdUnitKeyword;
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
public interface AdUnitKeywordDao{
    String TABLE_NAME=" ad_unit_keyword ";
    String INSERT_FIELDS=" unit_id,keyword ";
    String SELECT_FIELDS=" id,"+INSERT_FIELDS;
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (unit_id=#{unitId},keyword=#{keyword})"})
    int insertUnitKeyWord(AdUnitKeyword adUnitKeyword);
    default List<AdUnitKeyword> insertAndSelectAllUnitKeyWord(List<AdUnitKeyword> unitKeywords){
        List<AdUnitKeyword> adUnitKeywords=new ArrayList<>();
        for (AdUnitKeyword adUnitKeyword:unitKeywords) {
            insertUnitKeyWord(adUnitKeyword);
            adUnitKeywords.add(adUnitKeyword);
        }
        return adUnitKeywords;
    }
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME})
    List<AdUnitKeyword> selectAll();
}
