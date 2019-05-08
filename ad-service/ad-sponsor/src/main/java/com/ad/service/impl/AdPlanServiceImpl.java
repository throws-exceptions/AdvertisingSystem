package com.ad.service.impl;

import com.ad.constant.CommonStatus;
import com.ad.constant.Constants;
import com.ad.dao.AdPlanDao;
import com.ad.dao.AdUserDao;

import com.ad.entity.AdPlan;
import com.ad.entity.AdUser;
import com.ad.exception.AdException;
import com.ad.service.IAdPlanService;
import com.ad.utils.CommonUtils;
import com.ad.vo.AdPlanGetRequest;
import com.ad.vo.AdPlanRequest;
import com.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * created by Mr.F on 2019/5/2
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserDao adUserDao;
    private final AdPlanDao adplanDao;

    @Autowired
    public AdPlanServiceImpl(AdUserDao adUserDao,
                             AdPlanDao adplanDao) {
        this.adUserDao = adUserDao;
        this.adplanDao = adplanDao;
    }

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request)
            throws AdException {

        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 确保关联的 User 是存在的
        Optional<AdUser> adUser = Optional.ofNullable(adUserDao.selectById(request.getUserId()));
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdPlan oldPlan = adplanDao.selectByUserIdAndPlanName(
                request.getUserId(), request.getPlanName()
        );
        if (oldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        int temp=adplanDao.insertPlan(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );
        if(temp<=0){
            throw new AdException(Constants.ErrorMsg.INSERT_FAILED);
        }
        AdPlan newAdPlan = adplanDao.selectByUserId(request.getUserId());

        return new AdPlanResponse(newAdPlan.getId(),
                newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request)
            throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return adplanDao.selectAllByIdAndUserId(
                request.getIds(), request.getUserId()
        );
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request)
            throws AdException {

        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adplanDao.selectByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate())
            );
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate())
            );
        }


        adplanDao.updatePlan(plan);
        plan = adplanDao.selectByIdAndUserId(request.getId(), request.getUserId());

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adplanDao.selectByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adplanDao.updatePlan(plan);
    }
}
