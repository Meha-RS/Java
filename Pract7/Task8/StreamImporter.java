package Pract7.Task8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class StreamImporter {
    public static StreamStats process(Path file, int maxErrors, int cancelThreshold) throws IOException {
        long totalLines = 0;
        long validLines = 0;
        long errorLines = 0;
        List<String> errors = new ArrayList<>();
        boolean cancelled = false;

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalLines++;

                boolean isValid = !line.trim().isEmpty() && line.contains(";");

                if (isValid) {
                    validLines++;
                } else {
                    errorLines++;

                    if (errors.size() < maxErrors) {
                        errors.add("Строка " + totalLines + ": битые данные");
                    }

                    if (errorLines >= cancelThreshold) {
                        cancelled = true;
                        break;
                    }
                }
            }
        }

        return new StreamStats(totalLines, validLines, errorLines, errors, cancelled);
    }
}