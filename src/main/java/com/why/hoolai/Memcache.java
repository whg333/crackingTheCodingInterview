package com.why.hoolai;

public interface Memcache<K, V> {

    void reSize(int size);
    
    V get(K key);
    
    void put(K key, V value);
    
    void delete(K key);
    
    void update(K key, V value);
    
}
