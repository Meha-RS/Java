package Pract7.Task7;

import java.util.List;

public record ImportResult(
        boolean success,
        List<Course> validCourses,
        List<String> errors
) {
    public static ImportResult success(List<Course> courses) {
        return new ImportResult(true, courses, List.of());
    }

    public static ImportResult failure(List<String> errors) {
        return new ImportResult(false, List.of(), errors);
    }
}