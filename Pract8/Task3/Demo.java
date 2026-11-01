package Pract8.Task3;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Integer> durations = List.of(30, 40, 50);

        int sum = durations.stream().reduce(0, Integer::sum);
        System.out.println("Сумма: " + sum);

        int wrongSum = durations.stream().reduce(100, Integer::sum);
        System.out.println("Неправильная сумма: " + wrongSum + " (прибавили 100)");
    }
}