package Pract7.Task1;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException{
        Path goodfile = Path.of("Pract7","Task1", "course.txt");
        Path missingFile = Path.of("Pract7","Task1", "no_such_file.txt");
        Path dirAsFile = Path.of("Pract7","Task1", "some_dir");


        List<String> course = List.of("Java Basics", "Java OOP", "Java Files");
        List<String> loaded = List.of();

        try {
            CourseCatalog.save(goodfile, course);
            loaded = CourseCatalog.load(goodfile);
            System.out.println("Прочитано курсов: " + loaded.size());
            loaded.forEach(c -> System.out.println(" - " + c));
        } catch (IOException e) {
            System.err.println("Ошибка сохранения данных: " + e.getMessage());
        }

        try{
            CourseCatalog.load(missingFile);
            System.err.println("Ошибка: ожидали NoSuchFileException");
        } catch (NoSuchFileException e) {
            System.out.println("Поймали: " + e.getMessage());
        }

        try {
            Files.createDirectories(dirAsFile);
            CourseCatalog.load(dirAsFile);
            System.err.println("Ошибка: ожидали IOException для каталога");
        } catch (IOException e){
            System.out.println("Поймали: " + e.getMessage());
        }

    }
}