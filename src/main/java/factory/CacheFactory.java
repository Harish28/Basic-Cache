package factory;

import Cache.Cache;
import Storage.HashMapBasedStorage;
import policies.LRUEvictionPolicy;
public class CacheFactory<Key,Value> {
    public Cache<Key, Value> getDefaultCache(int capacity) {
        return new Cache<Key, Value>(new HashMapBasedStorage<>(capacity), new LRUEvictionPolicy<>());
    }
}
