package com.ad.index.adplan;

import com.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * created by Mr.F on 2019/5/4
 */
@Slf4j
@Component
public class AdPlanIndex implements IndexAware<Long,AdPlanObject> {
    private static Map<Long,AdPlanObject> adPlanObjectMap;
    static {
        adPlanObjectMap=new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return adPlanObjectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        if(value!=null){
            log.info("before add : {}",adPlanObjectMap);
            adPlanObjectMap.put(key,value);
            log.info("after add : {}",adPlanObjectMap);
        }else{
            log.error("添加adPlanObjectMap时传入参数为空");
        }
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        log.info("before add : {}",adPlanObjectMap);
        AdPlanObject oldAdPlanObject=adPlanObjectMap.get(key);
        if(oldAdPlanObject==null){
            adPlanObjectMap.put(key, value);
        }else{
            //这里不需要再放入，因为我们直接把引用的对象中的值改变了，到时候可能需要重点检查一下
            oldAdPlanObject.updateAdPlanObject(value);
        }
        log.info("after add : {}",adPlanObjectMap);
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        log.info("before add : {}",adPlanObjectMap);
        adPlanObjectMap.remove(key,value);
        log.info("after add : {}",adPlanObjectMap);
    }
}
