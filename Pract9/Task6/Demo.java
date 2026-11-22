package Pract9.Task6;

import Pract9.Common.Runner;

public class Demo {
    public static void main(String[] args) throws Exception {
        int capacity = 50;
        int threads = 8;
        int studentsPerThread = 100;

        System.out.println("--- Тест SyncCourse ---");
        test(new SyncCourse(capacity), threads, studentsPerThread, capacity);

        System.out.println("\n--- Тест ConcurrentCourse ---");
        test(new ConcurrentCourse(capacity), threads, studentsPerThread, capacity);
    }

    static void test(Course course, int threads, int studentsPerThread, int capacity) throws Exception {
        Runner.go(threads, studentsPerThread, () -> {
            for (int i = 0; i < studentsPerThread; i++) {
                course.enroll("student-" + i);
            }
        });

        System.out.println("Ожидали: " + capacity + ", Записалось: " + course.size());
        if (course.size() == capacity) {
            System.out.println("Успех! Инварианты соблюдены.");
        } else {
            System.out.println("ОШИБКА! Инварианты нарушены.");
        }
    }
}