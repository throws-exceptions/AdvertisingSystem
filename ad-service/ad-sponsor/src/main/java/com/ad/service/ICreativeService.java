package com.ad.service;


import com.ad.exception.AdException;
import com.ad.vo.CreativeRequest;
import com.ad.vo.CreativeResponse;

/**
 * created by Mr.F on 2019/5/2
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
