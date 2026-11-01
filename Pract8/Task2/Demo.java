package Pract8.Task2;

import Pract8.Model.CourseWithModules;
import Pract8.Model.Module;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<CourseWithModules> courses = List.of(
            new CourseWithModules("Java", List.of(new Module("OOP"), new Module("Files"))),
            new CourseWithModules("Web", List.of(new Module("OOP"), new Module("HTML")))
        );

        List<String> topics = courses.stream()
            .flatMap(c -> c.modules().stream())
            .map(m -> m.topic().toLowerCase().trim())
            .distinct()
            .toList();
        System.out.println("Темы: " + topics);

        Map<String, List<String>> index = courses.stream()
            .flatMap(c -> c.modules().stream().map(m -> Map.entry(m.topic(), c.code())))
            .collect(Collectors.groupingBy(
                Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
            ));
        System.out.println("Индекс: " + index);
    }
}