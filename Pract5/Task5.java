import java.util.*;

public class Task5{
    public static void main(String[] args){
        //1) Воспроизводим ошибку
        List<String> broken = new ArrayList<>(List.of("A", "B", "C"));
        try{
            for(String cur : broken){
                if(cur.equals("B"))
                    broken.remove(cur);
            }
        } catch(ConcurrentModificationException e){
            System.out.println("Catch: " + e);
        }

        //2) Исправление через Iterator
        List<String> iteratorFixed = new ArrayList<>(List.of("A", "B", "C"));
        Iterator<String> it = iteratorFixed.iterator();
        while(it.hasNext()){
            if(it.next().equals("B"))
                it.remove();
        }
        System.out.println("Iterator fix: " + iteratorFixed);

        //3) Исправление через removeIf
        List<String> removeIfFixed = new ArrayList<>(List.of("A", "B", "C"));
        removeIfFixed.removeIf(cur -> cur.equals("B"));
        System.out.println("removeIf fix: " + removeIfFixed);
    }
}