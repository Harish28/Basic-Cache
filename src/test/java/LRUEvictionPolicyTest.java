package test.java;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import policies.LRUEvictionPolicy;

import static org.junit.jupiter.api.Assertions.*;

class LRUEvictionPolicyTest {
    private LRUEvictionPolicy<Integer> lruEvictionPolicy;

    @BeforeEach
    void setUp() {
        lruEvictionPolicy = new LRUEvictionPolicy<>();
    }

    @Test
    void testNoKeyToEvictInitially() {
        assertNull(lruEvictionPolicy.evictAKey());
    }

    @Test
    void testKeysAreEvictedInTheOrderInWhichTheyAreAccessedAccess() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(4);
        assertEquals(1, lruEvictionPolicy.evictAKey());
        assertEquals(2, lruEvictionPolicy.evictAKey());
        assertEquals(3, lruEvictionPolicy.evictAKey());
        assertEquals(4, lruEvictionPolicy.evictAKey());
    }

    @Test
    void testReaccesingKeyPreventsItFromEviction() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(4);
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(5);
        assertEquals(3, lruEvictionPolicy.evictAKey());
        assertEquals(2, lruEvictionPolicy.evictAKey());
        assertEquals(4, lruEvictionPolicy.evictAKey());
        assertEquals(1, lruEvictionPolicy.evictAKey());
        assertEquals(5, lruEvictionPolicy.evictAKey());
    }
}