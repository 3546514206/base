package edu.zjnu.arithmetic.map;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-17
 **/
public class HashMap<K, V> implements Map<K, V> {

    Entry<K, V>[] table = null;

    public HashMap() {
        table = new Entry[16];
    }

    @Override
    public V put(K k, V v) {

        return null;
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    class Entry<K, V> implements Map.Entry<K, V> {

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }

}
