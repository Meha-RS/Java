import java.util.*;

public class Task2{
    public static void main(String[] args){
        //1) Коды курсов с дубликатами
        List<String> codes = new ArrayList<>(List.of("CS101", "MA101", "CS101", "PH101", "MA101"));

        //2) LinkedHashSet: уникальность + порядок первого появления
        Set<String> unique = new LinkedHashSet<>(codes);
        System.out.println("Unique: " + unique);

        //3) Для сравнения — HashSet (порядок не гарантирован)
        Set<String> unordered = new HashSet<>(codes);
        System.out.println("HashSet: " + unordered);
    }
}