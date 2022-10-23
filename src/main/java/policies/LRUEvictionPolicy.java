package policies;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final Deque<Key> queue;
    private final HashSet<Key> mapper;

    public LRUEvictionPolicy() {
        this.queue = new LinkedList<>();
        this.mapper = new HashSet<>();
    }

    @Override
    public void keyAccessed(Key k) {
        if(mapper.contains(k)) {
            queue.remove(k);
            queue.addFirst(k);
        } else {
            queue.addFirst(k);
            mapper.add(k);
        }
    }

    @Override
    public Key evictAKey() {
        if(queue.isEmpty()) {
            return null;
        }
        Key key = queue.getLast();
        queue.removeLast();
        mapper.remove(key);
        return key;
    }
}
