package com.ad.index;

/**
 * created by Mr.F on 2019/5/4
 */

/**
 * 该接口定义了索引的增删改查的方法，不是所有的表，表中的所有字段都要创建索引
 * @param <K>
 * @param <V>
 */
public interface IndexAware<K,V> {
    V get(K key);
    void add(K key,V value);
    void update(K key,V value);
    void delete(K key,V value);
}
