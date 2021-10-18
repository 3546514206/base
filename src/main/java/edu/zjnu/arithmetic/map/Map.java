package edu.zjnu.arithmetic.map;

/**
 * @description: Map
 * @author: 杨海波
 * @date: 2021-10-17
 **/
public interface Map<K, V> {

    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry<K, V> {

        K getKey();

        V getValue();
    }

}
