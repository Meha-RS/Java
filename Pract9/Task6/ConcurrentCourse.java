package Pract9.Task6;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentCourse implements Course {
    private final int capacity;
    private final ConcurrentHashMap<String, Boolean> students = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    public ConcurrentCourse(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean enroll(String studentId) {
        while (true) {
            int current = count.get();
            if (current >= capacity)
                return false;

            if (count.compareAndSet(current, current + 1)) {
                if (students.putIfAbsent(studentId, true) != null) {
                    count.decrementAndGet();
                    return false;
                }
                return true;
            }
        }
    }

    @Override
    public int size() {
        return count.get();
    }
}