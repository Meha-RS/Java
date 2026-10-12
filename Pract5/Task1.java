import java.util.*;

public class Task1{
    public static void main(String[] args){
        //1) Дублиикаты:
        List<String> registrer = new ArrayList<>(List.of("A", "B", "C", "B", "D"));

        //2) Вставка в середину (индекс 2) и удаление первого B
        registrer.add(2, "X");
        registrer.remove("B");
        System.out.println("Register: " + registrer);

        //3) Поиск дубликатов: идём по списку, кладём в Set
        Set<String> seen = new HashSet<>();
        Set<String> dups = new LinkedHashSet<>();
        registrer.add(5, "B");
        System.out.println("\nRegister(+ B): " + registrer);
        for(String cur: registrer){
            if(!seen.add(cur))
                dups.add(cur);
        }
        System.out.println("Duplicates: " + dups);

        // 4) Сравнение ArrayList vs LinkedList
        List<Integer> arr = new ArrayList<>();
        List<Integer> lnk = new LinkedList<>();
        for(int i = 0; i < 100_000; ++i){
            arr.add(i);
            lnk.add(i);
        }

        long sA = 0, sL = 0;
        long t0 = System.nanoTime();
        for(int i = 0; i < arr.size(); ++i)
            sA += arr.get(i);
        long t1 = System.nanoTime();
        for(int i = 0;i < lnk.size(); ++i)
            sL += lnk.get(i);
        long t2 = System.nanoTime();
        System.out.println("\nIndex:\n" + 
            "ArrayList: " + (t1 - t0) + " ns\n" +
            "LinkedList: " + (t2 - t1) + " ns"
        );

        long fA = 0, fL = 0;
        t0 = System.nanoTime();
        for(int x : arr)
            fA += x;
        t1 = System.nanoTime();
        for(int x: lnk)
            fL += x;
        t2 = System.nanoTime();
        System.out.println("\nForEach:\n" + 
            "ArrayList: " + (t1 - t0) + " ns\n" +
            "LinkedList: " + (t2 - t1) + " ns"
        );
    }
}
