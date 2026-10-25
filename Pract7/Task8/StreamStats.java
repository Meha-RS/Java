package Pract7.Task8;

import java.util.List;

public record StreamStats(
        long totalLines,
        long validLines,
        long errorLines,
        List<String> errors,
        boolean cancelled
) {
}