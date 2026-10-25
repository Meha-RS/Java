package Pract7.Task1;

import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import  java.util.List;

public class CourseCatalog {
    public static void save(Path file, List<String> courses) throws IOException{
        String text = String.join("\n", courses);
        Files.writeString(file, text, StandardCharsets.UTF_8);
    }

    public static List<String> load(Path file)throws IOException{
        if(!Files.exists(file))
            throw new NoSuchFileException("Отсутствует файл в каталоге: " + file);

        if(Files.isDirectory(file))
            throw new IOException("По пути каталог, а не файл: " + file);

        String text = Files.readString(file, StandardCharsets.UTF_8);
        return List.of(text.split("\n", -1));
    }

}