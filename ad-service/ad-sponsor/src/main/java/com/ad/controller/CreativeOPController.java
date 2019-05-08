package com.ad.controller;

import com.ad.exception.AdException;
import com.alibaba.fastjson.JSON;
import com.ad.service.ICreativeService;
import com.ad.vo.CreativeRequest;
import com.ad.vo.CreativeResponse;
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
public class CreativeOPController {

    private final ICreativeService creativeService;

    @Autowired
    public CreativeOPController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(
            @RequestBody CreativeRequest request
            ) throws AdException {
        log.info("ad-sponsor: createCreative -> {}",
                JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
