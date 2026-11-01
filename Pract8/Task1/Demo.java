package Pract8.Task1;

import Pract8.Model.Course;
import java.util.Comparator;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Course> courses = List.of(
            new Course("C2", 2, 40),
            new Course("C1", 1, 30),
            new Course("C3", 1, 30)
        );

        List<Course> sorted = courses.stream()
            .sorted(Comparator.comparingInt(Course::level)
                .thenComparingInt(Course::duration)
                .thenComparing(Course::code))
            .toList();

        System.out.println(sorted);
    }
}