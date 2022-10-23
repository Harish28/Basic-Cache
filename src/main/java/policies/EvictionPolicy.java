package policies;

public interface EvictionPolicy<Key> {
    /**
     * Interface to define eviction policy
     * @param k
     */
    void keyAccessed(Key k);

    /**
     * Evict key and return it.
     */
    Key evictAKey();
}
