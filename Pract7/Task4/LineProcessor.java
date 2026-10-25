package Pract7.Task4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineProcessor {

    public static List<String> readCorrectly(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
            return lines.map(String::toUpperCase).collect(Collectors.toList());
        }
    }

    public static Stream<String> readIncorrectly(Path file) throws IOException {
        return Files.lines(file, StandardCharsets.UTF_8);
    }
}