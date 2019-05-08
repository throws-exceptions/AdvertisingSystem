package com.ad.service;

import com.ad.Application;
import com.ad.constant.CommonStatus;
import com.ad.dao.AdPlanDao;
import com.ad.dao.AdUnitDao;
import com.ad.dao.CreativeDao;
import com.ad.dao.unit_condition.AdUnitDistrictDao;
import com.ad.dao.unit_condition.AdUnitItDao;
import com.ad.dao.unit_condition.AdUnitKeywordDao;
import com.ad.dao.unit_condition.CreativeUnitDao;
import com.ad.dump.table.*;
import com.ad.entity.AdPlan;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.ad.entity.AdUnit;
import com.ad.entity.Creative;
import com.ad.entity.unit_condition.AdUnitDistrict;
import com.ad.entity.unit_condition.AdUnitIt;
import com.ad.entity.unit_condition.AdUnitKeyword;
import com.ad.entity.unit_condition.CreativeUnit;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

/**
 * created by Mr.F on 2019/5/7
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {
    //一次性将dao接口注入进来,由于注入较多，这里就不使用构造方法注入
    @Autowired
    private AdPlanDao adPlanDao;
    @Autowired
    private AdUnitDao adUnitDao;
    @Autowired
    private CreativeDao creativeDao;
    @Autowired
    private CreativeUnitDao creativeUnitDao;
    @Autowired
    private AdUnitDistrictDao adUnitDistrictDao;
    @Autowired
    private AdUnitItDao adUnitItDao;
    @Autowired
    private AdUnitKeywordDao adUnitKeywordDao;

    private void dumpAdPlanTable(String fileName){
        //获取数据表中有效状态的adplan
        List<AdPlan> adPlans=adPlanDao.selectAllByPlanStatus(CommonStatus.VALID.getStatus());
        if(CollectionUtils.isEmpty(adPlans)){
            return ;
        }
        //下面实现了将上面adPlans中的记录转换为adPlanTables中的记录
        List<AdPlanTable> adPlanTables=new ArrayList<>();
        adPlans.forEach(p->adPlanTables.add(
                new AdPlanTable(
                        p.getId(),
                        p.getUserId(),
                        p.getPlanStatus(),
                        p.getStartDate(),
                        p.getEndDate()
                )
        ));

        Path path= Paths.get(fileName);
        try(BufferedWriter writer=Files.newBufferedWriter(path)){
            for(AdPlanTable adPlanTable:adPlanTables){
                //遍历写入JSON字符串
                writer.write(JSON.toJSONString(adPlanTable));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            log.error("dumpAdPlanTable errer -> {}",e.getMessage());
        }

    }
    private void dumpAdUnitTable(String fileName){
        List<AdUnit> adUnits=adUnitDao.selectByUnitStatus(
                CommonStatus.VALID.getStatus()
        );
        if(CollectionUtils.isEmpty(adUnits)){
            return ;
        }
        List<AdUnitTable> adUnitTables=new ArrayList<>();
        adUnits.forEach(u->adUnitTables.add(
                new AdUnitTable(
                        u.getId(),
                        u.getUnitStatus(),
                        u.getPositionType(),
                        u.getPlanId()
                )
        ));

        Path path= Paths.get(fileName);
        try(BufferedWriter writer=Files.newBufferedWriter(path)){
            for(AdUnitTable adUnitTable:adUnitTables){
                //遍历写入JSON字符串
                writer.write(JSON.toJSONString(adUnitTable));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            log.error("dumpAdUnitTable errer -> {}",e.getMessage());
        }
    }
    private void dumpCreativeTable(String fileName){
        List<Creative> creatives=creativeDao.selectAll();

        if(CollectionUtils.isEmpty(creatives)){
            return ;
        }
        List<AdCreativeTable> adCreativeTables=new ArrayList<>();
        creatives.forEach(c->adCreativeTables.add(
                new AdCreativeTable(
                        c.getId(),
                        c.getName(),
                        c.getType(),
                        c.getMaterialType(),
                        c.getHeight(),
                        c.getWidth(),
                        c.getAuditStatus(),
                        c.getUrl()
                )
        ));

        Path path= Paths.get(fileName);
        try(BufferedWriter writer=Files.newBufferedWriter(path)){
            for(AdCreativeTable adCreativeTable:adCreativeTables){
                //遍历写入JSON字符串
                writer.write(JSON.toJSONString(adCreativeTable));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            log.error("dumpAdCreativeTable errer -> {}",e.getMessage());
        }
    }
    private void dumpAdCreativeUnitTable(String fileName) {

        List<CreativeUnit> creativeUnits = creativeUnitDao.selectAll();
        if (CollectionUtils.isEmpty(creativeUnits)) {
            return;
        }

        List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(c -> creativeUnitTables.add(
                new AdCreativeUnitTable(
                        c.getCreativeId(),
                        c.getUnitId()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeUnitTable creativeUnitTable : creativeUnitTables) {
                writer.write(JSON.toJSONString(creativeUnitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdCreativeUnit error");
        }
    }

    private void dumpAdUnitDistrictTable(String fileName) {

        List<AdUnitDistrict> adunitDistricts = adUnitDistrictDao.selectAll();
        if (CollectionUtils.isEmpty(adunitDistricts)) {
            return;
        }

        List<AdUnitDistrictTable> adunitDistrictTables = new ArrayList<>();
        adunitDistricts.forEach(d -> adunitDistrictTables.add(
                new AdUnitDistrictTable(
                        d.getUnitId(),
                        d.getProvince(),
                        d.getCity()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitDistrictTable adunitDistrictTable : adunitDistrictTables) {
                writer.write(JSON.toJSONString(adunitDistrictTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdUnitDistrictTable error");
        }
    }

    private void dumpAdUnitItTable(String fileName) {

        List<AdUnitIt> adunitIts = adUnitItDao.selectAll();
        if (CollectionUtils.isEmpty(adunitIts)) {
            return;
        }

        List<AdUnitItTable> adunitItTables = new ArrayList<>();
        adunitIts.forEach(i -> adunitItTables.add(
                new AdUnitItTable(
                        i.getUnitId(),
                        i.getItTag()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitItTable adunitItTable : adunitItTables) {
                writer.write(JSON.toJSONString(adunitItTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdUnitItTable error");
        }
    }

    private void dumpAdUnitKeywordTable(String fileName) {

        List<AdUnitKeyword> adunitKeywords = adUnitKeywordDao.selectAll();
        if (CollectionUtils.isEmpty(adunitKeywords)) {
            return;
        }

        List<AdUnitKeywordTable> adunitKeywordTables = new ArrayList<>();
        adunitKeywords.forEach(k -> adunitKeywordTables.add(
                new AdUnitKeywordTable(
                        k.getUnitId(),
                        k.getKeyword()
                )
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitKeywordTable adunitKeywordTable : adunitKeywordTables) {
                writer.write(JSON.toJSONString(adunitKeywordTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            log.error("dumpAdUnitItTable error");
        }
    }

}
