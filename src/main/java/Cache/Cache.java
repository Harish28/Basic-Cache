package Cache;

import Exceptions.KeyNotFound;
import Exceptions.StorageFullException;
import Storage.IStorage;
import policies.EvictionPolicy;

public class Cache<Key,Value> {
    private final IStorage<Key,Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;
    public Cache(IStorage<Key,Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage =  storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key k, Value v) {
        try {
            storage.add(k,v);
            evictionPolicy.keyAccessed(k);
        } catch (StorageFullException e) {
            Key evictedKey = evictionPolicy.evictAKey();
            if(evictedKey == null) {
                throw new RuntimeException("Eviction failed...");
            } else {
                System.out.println("Key " + evictedKey + " is Evicted...");
            }
            storage.removeKey(evictedKey);
            put(k, v);
        }
    }


    public Value get(Key k) {
        try {
            Value v = storage.get(k);
            evictionPolicy.keyAccessed(k);
            return v;
        } catch (KeyNotFound e) {
            System.out.println("Key " + k + " Not found...");
            return null;
        }
    }
}
