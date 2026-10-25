package Pract7.Task5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BinaryStorage {
    private static final int MAGIC = 0x43525345; // "CRSE"
    private static final int VERSION = 1;

    public static void save(Path file, List<CourseRecord> courses) throws IOException {
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file))) {
            out.writeInt(MAGIC);
            out.writeInt(VERSION);
            out.writeInt(courses.size());

            for (CourseRecord c : courses) {
                out.writeUTF(c.getTitle());
                out.writeInt(c.getHours());
            }
        }
    }

    public static List<CourseRecord> load(Path file) throws IOException {
        try (DataInputStream in = new DataInputStream(Files.newInputStream(file))) {
            if (in.readInt() != MAGIC)
                throw new IOException("Неверный формат файла");

            int version = in.readInt();
            if (version != VERSION)
                throw new IOException("Неизвестная версия: " + version);

            int count = in.readInt();
            List<CourseRecord> result = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                String title = in.readUTF();      // Если файл обрезан, вылетит EOFException
                int hours = in.readInt();
                result.add(new CourseRecord(title, hours));
            }
            return result;
        }
    }
}