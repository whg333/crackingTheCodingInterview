package com.why.hoolai;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class QueueMemcache<K, V> implements Memcache<K, V> {

    private LinkedHashMap<K, V> map;
    
    @SuppressWarnings("serial")
    public QueueMemcache(final int size){
        this.map = new LinkedHashMap<K, V>(size){
            protected boolean removeEldestEntry(java.util.Map.Entry<K,V> eldest) {
                return size() > size;
            };
        };
    }
    
    @SuppressWarnings("serial")
    @Override
    public void reSize(final int size) {
        LinkedHashMap<K, V> oldMap = map;
        map = new LinkedHashMap<K, V>(size){
            protected boolean removeEldestEntry(java.util.Map.Entry<K,V> eldest) {
                return size() > size;
            };
        };
        map.putAll(oldMap);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void delete(K key) {
        map.remove(key);
    }

    @Override
    public void update(K key, V value) {
        put(key, value);
    }
    
    public Collection<V> values() {
        return map.values();
    }
    
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
    
    public static void main(String[] args) {
        QueueMemcache<String, Integer> memcache = new QueueMemcache<String, Integer>(3);
        memcache.put("3", 3);
        memcache.put("1", 1);
        memcache.put("5", 5);
        memcache.put("4", 4);
        memcache.put("6", 6);
        System.out.println(memcache.entrySet());
        
        System.out.println(memcache.get("3"));
        System.out.println(memcache.get("5"));
        
        memcache.reSize(2);
        System.out.println(memcache.entrySet());
        
        System.out.println(memcache.get("4"));
        System.out.println(memcache.get("5"));
    }

}
