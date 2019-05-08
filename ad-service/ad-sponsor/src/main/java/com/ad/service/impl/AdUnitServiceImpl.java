package com.ad.service.impl;


import com.ad.constant.Constants;
import com.ad.dao.AdPlanDao;
import com.ad.dao.AdUnitDao;
import com.ad.dao.CreativeDao;
import com.ad.dao.unit_condition.AdUnitDistrictDao;
import com.ad.dao.unit_condition.AdUnitItDao;
import com.ad.dao.unit_condition.AdUnitKeywordDao;
import com.ad.dao.unit_condition.CreativeUnitDao;
import com.ad.entity.AdPlan;
import com.ad.entity.AdUnit;
import com.ad.entity.unit_condition.AdUnitDistrict;
import com.ad.entity.unit_condition.AdUnitIt;
import com.ad.entity.unit_condition.AdUnitKeyword;
import com.ad.entity.unit_condition.CreativeUnit;
import com.ad.exception.AdException;
import com.ad.service.IAdUnitService;
import com.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mr.F on 2019/5/4.
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    private final AdPlanDao adplanDao;
    private final AdUnitDao adunitDao;

    private final AdUnitKeywordDao adunitKeywordDao;
    private final AdUnitItDao adunitItDao;
    private final AdUnitDistrictDao adunitDistrictDao;

    private final CreativeDao creativeDao;
    private final CreativeUnitDao creativeUnitDao;

    @Autowired
    public AdUnitServiceImpl(AdPlanDao planDao,
                             AdUnitDao unitDao,
                             AdUnitKeywordDao unitKeywordDao,
                             AdUnitItDao unitItDao,
                             AdUnitDistrictDao unitDistrictDao, CreativeDao creativeDao, CreativeUnitDao creativeUnitDao) {
        this.adplanDao = planDao;
        this.adunitDao = unitDao;
        this.adunitKeywordDao = unitKeywordDao;
        this.adunitItDao = unitItDao;
        this.adunitDistrictDao = unitDistrictDao;
        this.creativeDao = creativeDao;
        this.creativeUnitDao = creativeUnitDao;
    }

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request)
            throws AdException {

        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlan =
                Optional.ofNullable(adplanDao.selectByUserId(request.getPlanId()));
        if (!adPlan.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdUnit oldAdUnit = adunitDao.selectByPlanIdAndUnitName(
                request.getPlanId(), request.getUnitName()
        );
        if (oldAdUnit != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }
        int temp=adunitDao.insertUnit(
                new AdUnit(request.getPlanId(), request.getUnitName(),
                        request.getPositionType(), request.getBudget())
        );
        if(temp<=0){
            throw new AdException(Constants.ErrorMsg.INSERT_FAILED);
        }
        AdUnit newAdUnit = adunitDao.selectByPlanIdAndUnitName(
                request.getPlanId(),request.getUnitName()
                );

        return new AdUnitResponse(newAdUnit.getId(),
                newAdUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(
            AdUnitKeywordRequest request) throws AdException {

        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<Long> ids = Collections.emptyList();

        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getUnitKeywords())) {

            request.getUnitKeywords().forEach(i -> unitKeywords.add(
                    new AdUnitKeyword(i.getUnitId(), i.getKeyword())
            ));
            ids = adunitKeywordDao.insertAndSelectAllUnitKeyWord(unitKeywords).stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }

        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(
            AdUnitItRequest request) throws AdException {

        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(
                new AdUnitIt(i.getUnitId(), i.getItTag())
        ));
        List<Long> ids = adunitItDao.insertAndSelectAllUnitIt(unitIts).stream()
                .map(AdUnitIt::getId)
                .collect(Collectors.toList());

        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(
            AdUnitDistrictRequest request) throws AdException {

        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(d -> unitDistricts.add(
                new AdUnitDistrict(d.getUnitId(), d.getProvince(),
                        d.getCity())
        ));
        List<Long> ids = adunitDistrictDao.insertAndSelectAllUnitDistrict(unitDistricts)
                .stream().map(AdUnitDistrict::getId)
                .collect(Collectors.toList());

        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(
            CreativeUnitRequest request) throws AdException {

        List<Long> unitIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getCreativeId)
                .collect(Collectors.toList());

        if (!(isRelatedUnitExist(unitIds) && isRelatedUnitExist(creativeIds))) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(
                new CreativeUnit(i.getCreativeId(), i.getUnitId())
        ));

        List<Long> ids = creativeUnitDao.insertAndSelectAllCreativeUnit(creativeUnits)
                .stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());

        return new CreativeUnitResponse(ids);
    }

    /**
     * 保证传进来的unitIds不会重复，return那边使用了hashset来达到校验
     * @param unitIds
     * @return
     */
    private boolean isRelatedUnitExist(List<Long> unitIds) {

        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }

        return adunitDao.selectAllById(unitIds).size() ==
                new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> creativeIds) {

        if (CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }

        return creativeDao.selectAllById(creativeIds).size() ==
                new HashSet<>(creativeIds).size();
    }
}
