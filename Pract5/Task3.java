import java.util.*;

public class Task3{
    public static void main(String[] args){
        Map<String, Course> index = new HashMap<>();

        Course first = new Course("CS101", "Intro");
        Course duplicate = new Course("CS101", "Duplicate");

        //1) putIfAbsent: вставит, только если ключа нет; вернёт предыдущее
        Course previous = index.putIfAbsent(first.getId(), first);
        if(previous != null){
            System.out.println("Already exists: " + previous);
        }

        //2) computeIfAbsent: вычислит и вставит, только если ключа нет
        index.computeIfAbsent("MA101", id -> new Course(id, "Math"));

        //3) Повторная попытка — не перезапишет
        index.putIfAbsent("CS101", duplicate);

        System.out.println("containsKey: " + index.containsKey("CS101"));
        System.out.println("get: " + index.get("CS101"));
        System.out.println("index: " + index);
    }
}