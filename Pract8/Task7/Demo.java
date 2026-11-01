package Pract8.Task7;

import java.util.*;

public class Demo {
    record Event(String studentId, String courseCode, boolean completed) {}

    public static void main(String[] args) {
        List<Event> events = List.of(
            new Event("S1", "Java", true),
            new Event("S2", "Java", false),
            new Event("S1", "Web", true),
            new Event("S3", "Unknown", true)
        );

        Set<String> students = new HashSet<>();
        Map<String, Integer> total = new HashMap<>();
        Map<String, Integer> done = new HashMap<>();
        List<String> anomalies = new ArrayList<>();

        for (Event e : events) {
            students.add(e.studentId());

            if (!e.courseCode().equals("Java") && !e.courseCode().equals("Web")) {
                anomalies.add("Неизвестный курс: " + e.courseCode());
                continue;
            }

            total.merge(e.courseCode(), 1, Integer::sum);
            if (e.completed()) {
                done.merge(e.courseCode(), 1, Integer::sum);
            }
        }

        Map<String, Double> completionRate = new HashMap<>();
        for (String c : total.keySet()) {
            completionRate.put(c, (double) done.getOrDefault(c, 0) / total.get(c));
        }

        System.out.println("Уникальные студенты: " + students);
        System.out.println("Completion rate: " + completionRate);
        System.out.println("Аномалии: " + anomalies);
    }
}