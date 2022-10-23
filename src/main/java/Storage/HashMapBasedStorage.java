package Storage;

import Exceptions.KeyNotFound;
import Exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key,Value> implements IStorage<Key,Value> {
    private final Map<Key,Value> store = new HashMap<>();
    private final int capacity;

    public HashMapBasedStorage(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void add(Key k, Value v) {
        if(store.size() == capacity) {
            throw new StorageFullException("Cache is full...");
        }
        store.put(k,v);
    }

    @Override
    public Value get(Key k) {
        if(store.containsKey(k)) {
            throw new KeyNotFound("Key not found in cache...");
        }
        return store.get(k);
    }

    @Override
    public void removeKey(Key k) {
        if(store.containsKey(k)) {
            throw new KeyNotFound("Key not found in cache...");
        }
        store.remove(k);
    }
}
