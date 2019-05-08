package com.ad.service.impl;


import com.ad.constant.Constants;
import com.ad.dao.CreativeDao;
import com.ad.entity.Creative;
import com.ad.exception.AdException;
import com.ad.service.ICreativeService;
import com.ad.vo.CreativeRequest;
import com.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.F on 2019/5/4.
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeDao creativeDao;

    @Autowired
    public CreativeServiceImpl(CreativeDao creativeDao) {
        this.creativeDao = creativeDao;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        int temp=creativeDao.insertCreative(
                request.convertToEntity()
        );
        if(temp<=0){
            throw new AdException(Constants.ErrorMsg.INSERT_FAILED);
        }
        Creative creative = creativeDao.selectCreativeByUserId(request.getUserId());

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
