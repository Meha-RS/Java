package Pract8.Task6;

import Pract8.Model.Course;
import java.util.List;
import java.util.stream.Collector;

public class Demo {
    public static void main(String[] args) {
        List<Course> courses = List.of(
            new Course("C1", 1, 30),
            new Course("C2", 2, 40),
            new Course("C3", 1, 50)
        );

        String stats = courses.stream().collect(Collector.of(
            () -> new int[]{0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE},

            (arr, c) -> {
                arr[0]++;
                arr[1] += c.duration();
                arr[2] = Math.min(arr[2], c.duration());
                arr[3] = Math.max(arr[3], c.duration());
            },

            (arr1, arr2) -> new int[]{
                arr1[0] + arr2[0],
                arr1[1] + arr2[1],
                Math.min(arr1[2], arr2[2]),
                Math.max(arr1[3], arr2[3])
            },
            
            arr -> String.format("Count: %d, Sum: %d, Min: %d, Max: %d", arr[0], arr[1], arr[2], arr[3])
        ));

        System.out.println(stats);
    }
}