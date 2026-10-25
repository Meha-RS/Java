package Pract7.Task2;

import java.io.IOException;
import java.nio.file.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path input = Path.of("Pract7", "Task2", "input.txt");
        Path output = Path.of("Pract7", "Task2", "output.txt");

        String text = "Первая строка\nЭто вторая строка\nИ третья";

        TextProcessor.writeText(input, text);
        TextProcessor.processAndWrite(input, output);

        System.out.println("\nБайты: " + Files.size(input));
        System.out.println("Байты: " + Files.size(output));

    }
}