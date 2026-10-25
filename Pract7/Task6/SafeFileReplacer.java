package Pract7.Task6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class SafeFileReplacer {

    public static void replaceSafely(Path targetFile, String newContent) throws IOException {
        Path tempFile = Files.createTempFile(targetFile.getParent(), "temp_", ".tmp");

        try {
            Files.writeString(tempFile, newContent, StandardCharsets.UTF_8);
            try {
                Files.move(tempFile, targetFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
                System.out.println("Успех: файл заменен атомарно.");
            } catch (AtomicMoveNotSupportedException e) {
                System.out.println("Атомарный move не поддерживается! Делаем обычный move.");
                Files.move(tempFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}