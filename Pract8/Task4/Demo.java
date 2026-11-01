package Pract8.Task4;

import Pract8.Model.Course;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<Course> courses = List.of(
            new Course("C1", 1, 30),
            new Course("C2", 2, 40),
            new Course("C3", 1, 50)
        );

        Map<Integer, Map<String, Double>> summary = courses.stream()
            .collect(Collectors.groupingBy(
                Course::level,
                Collectors.collectingAndThen(
                    Collectors.summarizingInt(Course::duration),
                    stats -> Map.of("count", (double) stats.getCount(), "avg", stats.getAverage())
                )
            ));

        System.out.println(summary);
    }
}