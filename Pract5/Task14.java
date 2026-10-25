import java.util.*;

public class Task14{
    public static void main(String[] args){
        LruCache<String, String> cache = new LruCache<>(2);

        cache.put("A", "1");
        cache.put("B", "2");
        cache.get("A");
        cache.put("C", "3");
        System.out.println("Keys: " + cache.keySet());

        cache.put("A", "10");
        System.out.println("Keys: " + cache.keySet() + " A=" + cache.get("A"));

        try{
            cache.put(null, "x");
        }catch(IllegalArgumentException e){
            System.out.println("null key: " + e.getMessage());
        }
    }

    static class LruCache<K, T> extends LinkedHashMap<K, T>{
        private final int capacity;

        LruCache(int capacity){
            super(capacity, 0.75f, true);
            if(capacity <= 0)
                throw new IllegalArgumentException("capacity > 0");
            this.capacity = capacity;
        }

        @Override
        public T put(K key, T value){
            if(key == null || value == null)
                throw new IllegalArgumentException("null нельзя");
            return super.put(key, value);
        }

        @Override
        public T get(Object key){
            if(key == null)
                throw new IllegalArgumentException("null нельзя");
            return super.get(key);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, T> eldest){
            return size() > capacity;
        }
    }
}
