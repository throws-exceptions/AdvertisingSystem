package com.ad.index.creative;


import com.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by Mr.F on 2019/5/4
 */
@Slf4j
@Component
public class CreativeIndex implements IndexAware<Long, CreativeObject> {

    private static Map<Long, CreativeObject> creativeObjectMap;

    static {
        creativeObjectMap = new ConcurrentHashMap<>();
    }

    public List<CreativeObject> fetch(Collection<Long> adIds) {

        if (CollectionUtils.isEmpty(adIds)) {
            return Collections.emptyList();
        }

        List<CreativeObject> result = new ArrayList<>();

        adIds.forEach(u -> {
            CreativeObject object = get(u);
            if (null == object) {
                log.error("CreativeObject not found: {}", u);
                return;
            }

            result.add(object);
        });

        return result;
    }

    @Override
    public CreativeObject get(Long key) {
        return creativeObjectMap.get(key);
    }

    @Override
    public void add(Long key, CreativeObject value) {

        log.info("before add: {}", creativeObjectMap);
        creativeObjectMap.put(key, value);
        log.info("after add: {}", creativeObjectMap);
    }

    @Override
    public void update(Long key, CreativeObject value) {

        log.info("before update: {}", creativeObjectMap);

        CreativeObject oldObject = creativeObjectMap.get(key);
        if (null == oldObject) {
            creativeObjectMap.put(key, value);
        } else {
            oldObject.update(value);
        }

        log.info("after update: {}", creativeObjectMap);
    }

    @Override
    public void delete(Long key, CreativeObject value) {

        log.info("before delete: {}", creativeObjectMap);
        creativeObjectMap.remove(key);
        log.info("after delete: {}", creativeObjectMap);
    }
}
