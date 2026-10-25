package Pract7.Task8;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("Pract7", "Task8", "big_data.csv");
        Files.createDirectories(file.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (int i = 1; i <= 10000; i++) {
                if (i % 10 == 0) {
                    writer.write("BAD_LINE_WITHOUT_SEMICOLON"); // каждая 10-я строка битая
                } else {
                    writer.write("Java Course " + i + ";40");
                }
                writer.newLine();
            }
        }

        System.out.println("Файл создан. Запускаем импорт...");

        StreamStats stats = StreamImporter.process(file, 10, 50);

        System.out.println("Всего прочитано строк: " + stats.totalLines());
        System.out.println("Валидных: " + stats.validLines());
        System.out.println("Ошибок: " + stats.errorLines());
        System.out.println("Отменено: " + stats.cancelled());

        System.out.println("Сохраненные ошибки:");
        stats.errors().forEach(System.out::println);

        if (stats.totalLines() < 10000) {
            System.out.println("ВНИМАНИЕ: Файл прочитан не до конца, сработал порог отмены!");
        }
    }
}