import java.util.*;

public class Task4{
    public static void main(String[] args){
        Queue<String> queue = new ArrayDeque<>();

        queue.offer("Alice");   // мягкий: вернёт false при переполнении
        queue.add("Bob");       // строгий: бросит исключение

        System.out.println("Cheking head:");
        System.out.println("peek: " + queue.peek());       // null если пусто
        System.out.println("element: " + queue.element()); // исключение если пусто

        System.out.println("\nExtraction head:");
        System.out.println("poll: " + queue.poll());       // null если пусто
        System.out.println("remove: " + queue.remove());   // исключение если пусто

        System.out.println("\nEmpty queue:");
        System.out.println("poll empty: " + queue.poll());
    }
}