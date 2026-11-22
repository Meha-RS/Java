package Pract9.Task6;

import java.util.HashSet;
import java.util.Set;

public class SyncCourse implements Course {
    private final int capacity;
    private final Set<String> students = new HashSet<>();

    public SyncCourse(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized boolean enroll(String studentId) {
        if (students.size() >= capacity || students.contains(studentId)) {
            return false;
        }
        students.add(studentId);
        return true;
    }

    @Override
    public synchronized int size() {
        return students.size();
    }
}