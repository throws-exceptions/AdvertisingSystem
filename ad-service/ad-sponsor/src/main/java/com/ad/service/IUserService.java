package com.ad.service;

import com.ad.exception.AdException;
import com.ad.vo.CreateUserRequest;
import com.ad.vo.CreateUserResponse;

/**
 * created by Mr.F on 2019/5/1
 */

public interface IUserService {
    /**
     * 创建用户
     * @param request
     * @return
     * @throws AdException
     */
    CreateUserResponse createUser(CreateUserRequest request)throws AdException;
}
