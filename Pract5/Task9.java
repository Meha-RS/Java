import java.util.*;

public class Task9{
    public static void main(String[] args){
        List<Course> source = List.of(
            new Course("CS101", "Intro"),
            new Course("MA101", "Math")
        );
        List<Object> target = new ArrayList<>();

        copyInto(target, source);
        System.out.println("Target: " + target);
    }

    static void copyInto(List<? super Course> target, List<? extends Course> source){
        for(Course course : source)
            target.add(course);
    }
}