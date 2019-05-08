package com.ad.service;

import com.ad.entity.AdPlan;
import com.ad.exception.AdException;
import com.ad.vo.AdPlanGetRequest;
import com.ad.vo.AdPlanRequest;
import com.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * created by Mr.F on 2019/5/2
 */

public interface IAdPlanService {
    /**
     * 创建推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse createAdPlan(AdPlanRequest request)throws AdException;

    /**
     * 获取推广计划
     * @param request
     * @return
     * @throws AdException
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request)throws AdException;

    /**
     * 更新推广计划
     * @param request
     * @return
     * @throws AdException
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request)throws AdException;

    /**
     * 删除推广计划
     * @param request
     * @throws AdException
     */
    void deleteAdPlan(AdPlanRequest request)throws AdException;
}
