package Pract7.Task6;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path target = Path.of("Pract7", "Task6", "catalog.txt");
        Files.createDirectories(target.getParent());

        Files.writeString(target, "Старые данные", StandardCharsets.UTF_8);
        System.out.println("Было: " + Files.readString(target));

        SafeFileReplacer.replaceSafely(target, "НОВЫЕ ДАННЫЕ КАТАЛОГА");

        System.out.println("Стало: " + Files.readString(target));
    }
}