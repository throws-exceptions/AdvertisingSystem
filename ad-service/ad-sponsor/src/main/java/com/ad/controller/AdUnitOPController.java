package com.ad.controller;

import com.alibaba.fastjson.JSON;
import com.ad.exception.AdException;
import com.ad.service.IAdUnitService;
import com.ad.vo.AdUnitDistrictRequest;
import com.ad.vo.AdUnitDistrictResponse;
import com.ad.vo.AdUnitItRequest;
import com.ad.vo.AdUnitItResponse;
import com.ad.vo.AdUnitKeywordRequest;
import com.ad.vo.AdUnitKeywordResponse;
import com.ad.vo.AdUnitRequest;
import com.ad.vo.AdUnitResponse;
import com.ad.vo.CreativeUnitRequest;
import com.ad.vo.CreativeUnitResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Mr.F on 2019/5/2
 */
@Slf4j
@RestController
public class AdUnitOPController {

    private final IAdUnitService adUnitService;

    @Autowired
    public AdUnitOPController(IAdUnitService adUnitService) {
        this.adUnitService = adUnitService;
    }

    @PostMapping("/create/adUnit")
    public AdUnitResponse createUnit(
            @RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor: createUnit -> {}",
                JSON.toJSONString(request));
        return adUnitService.createUnit(request);
    }

    @PostMapping("/create/unitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(
            @RequestBody AdUnitKeywordRequest request
            ) throws AdException {
        log.info("ad-sponsor: createUnitKeyword -> {}",
                JSON.toJSONString(request));
        return adUnitService.createUnitKeyword(request);
    }

    @PostMapping("/create/unitIt")
    public AdUnitItResponse createUnitIt(
            @RequestBody AdUnitItRequest request
            ) throws AdException {
        log.info("ad-sponsor: createUnitIt -> {}",
                JSON.toJSONString(request));
        return adUnitService.createUnitIt(request);
    }

    @PostMapping("/create/unitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(
            @RequestBody AdUnitDistrictRequest request
            ) throws AdException {
        log.info("ad-sponsor: createUnitDistrict -> {}",
                JSON.toJSONString(request));
        return adUnitService.createUnitDistrict(request);
    }

    @PostMapping("/create/creativeUnit")
    public CreativeUnitResponse createCreativeUnit(
            @RequestBody CreativeUnitRequest request
            ) throws AdException {
        log.info("ad-sponsor: createCreativeUnit -> {}",
                JSON.toJSONString(request));
        return adUnitService.createCreativeUnit(request);
    }
}
