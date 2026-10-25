package Pract7.Task7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class CourseImporter {

    public static ImportResult importCourses(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        List<Course> validCourses = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty())
                continue;

            String[] parts = line.split(";");

            if (parts.length != 2) {
                errors.add("Строка " + (i + 1) + ": Неверный формат (ожидается 'Имя;Часы')");
                continue;
            }

            try {
                String name = parts[0].trim();
                int hours = Integer.parseInt(parts[1].trim());

                if (hours <= 0) {
                    errors.add("Строка " + (i + 1) + ": Часы должны быть > 0");
                    continue;
                }
                validCourses.add(new Course(name, hours));
            } catch (NumberFormatException e) {
                errors.add("Строка " + (i + 1) + ": Часы не являются числом");
            }
        }

        if (!errors.isEmpty()) {
            return ImportResult.failure(errors);
        }

        return ImportResult.success(validCourses);
    }
}