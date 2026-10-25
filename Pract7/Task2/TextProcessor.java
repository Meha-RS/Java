package Pract7.Task2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class TextProcessor {

    public static void writeText(Path file, String text) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write(text);
        }
    }

    public static void processAndWrite(Path inputFile, Path outputFile) throws IOException {
        int lineCount = 0;
        int charCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;

                charCount += line.length() + 1;

                String processedLine = lineCount + ": " + line.toUpperCase();
                writer.write(processedLine);
                writer.newLine();
            }
        }

        System.out.println("Обработано строк: " + lineCount);
        System.out.println("Обработано символов (включая \\n): " + (charCount - 1));
    }
}