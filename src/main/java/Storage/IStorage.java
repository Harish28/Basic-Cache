package Storage;

import Exceptions.KeyNotFound;
import Exceptions.StorageFullException;

public interface IStorage<Key,Value> {
    /**
     * Interface to define different types of storage
     * @param k
     * @param v
     * @throws StorageFullException
     */
    void add(Key k, Value v) throws StorageFullException;
    Value get(Key k) throws KeyNotFound;
    void removeKey(Key evictedKey) throws KeyNotFound;
}
