package Pract8.Task5;

import Pract8.Model.Course;
import java.util.Comparator;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Course> courses = List.of(
            new Course("C1", 1, 30),
            new Course("C2", 2, 60)
        );

        String longest = courses.stream()
            .max(Comparator.comparingInt(Course::duration))
            .map(Course::code)
            .orElse("Нет курсов");

        System.out.println("Самый длинный: " + longest);

        String empty = List.<Course>of().stream()
            .max(Comparator.comparingInt(Course::duration))
            .map(Course::code)
            .orElse("Пусто");
        System.out.println("Пустой список: " + empty);
    }
}