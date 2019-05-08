package com.ad.service.impl;

import com.ad.constant.Constants;
import com.ad.dao.AdUserDao;
import com.ad.entity.AdUser;
import com.ad.exception.AdException;
import com.ad.service.IUserService;
import com.ad.utils.CommonUtils;
import com.ad.vo.CreateUserRequest;
import com.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * created by Mr.F on 2019/5/1
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserDao adUserDao;
//    在spring4中官方建议使用构造器注入因为它允许一个应用程序组件实现为不可变对象，并确保所需的依赖项不是空。此外构造器注入组件总是返回一个完全初始化状态的client客户端(调用)。
    @Autowired
    public UserServiceImpl(AdUserDao adUserDao){
        this.adUserDao=adUserDao;
    }
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        String username=request.getUsername();
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUser=adUserDao.selectByUserName(username);
        if(oldUser!=null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        int temp=adUserDao.insertUser(new AdUser(username,CommonUtils.md5(username)));
        if(temp<=0){
            throw new AdException(Constants.ErrorMsg.INSERT_FAILED);
        }
        AdUser newUser=adUserDao.selectByUserName(username);

        return new CreateUserResponse(
                newUser.getId(),newUser.getUsername(),newUser.getToken(),
                newUser.getCreateTime(),newUser.getUpdateTime());
    }
}
