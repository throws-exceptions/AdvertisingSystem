package com.ad.index.adunit;


import com.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by Mr.F on 2019/5/4
 */
@Slf4j
@Component
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {

    private static Map<Long, AdUnitObject> adUnitObjectMap;

    static {
        adUnitObjectMap = new ConcurrentHashMap<>();
    }

    public Set<Long> match(Integer positionType) {

        Set<Long> adUnitIds = new HashSet<>();

        adUnitObjectMap.forEach((k, v) -> {
            if (AdUnitObject.isAdSlotTypeOK(positionType,
                    v.getPositionType())) {
                adUnitIds.add(k);
            }
        });

        return adUnitIds;
    }

    public List<AdUnitObject> fetch(Collection<Long> adUnitIds) {

        if (CollectionUtils.isEmpty(adUnitIds)) {
            return Collections.emptyList();
        }

        List<AdUnitObject> result = new ArrayList<>();

        adUnitIds.forEach(u -> {
            AdUnitObject object = get(u);
            if (object == null) {
                log.error("AdUnitObject not found: {}", u);
                return;
            }
            result.add(object);
        });

        return result;
    }

    @Override
    public AdUnitObject get(Long key) {
        return adUnitObjectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {

        log.info("before add: {}", adUnitObjectMap);
        adUnitObjectMap.put(key, value);
        log.info("after add: {}", adUnitObjectMap);
    }

    @Override
    public void update(Long key, AdUnitObject value) {

        log.info("before update: {}", adUnitObjectMap);

        AdUnitObject adUnitObject = adUnitObjectMap.get(key);
        if (null == adUnitObject) {
            adUnitObjectMap.put(key, value);
        } else {
            adUnitObject.update(value);
        }

        log.info("after update: {}", adUnitObjectMap);
    }

    @Override
    public void delete(Long key, AdUnitObject value) {

        log.info("before delete: {}", adUnitObjectMap);
        adUnitObjectMap.remove(key);
        log.info("after delete: {}", adUnitObjectMap);
    }
}
