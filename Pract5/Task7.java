import java.util.*;
import java.util.function.Function;

public class Task7{
    public static void main(String[] args){
        List<String> values = new ArrayList<>(List.of("a", "b", "c"));

        swap(values, 0, 2);
        System.out.println("Swapped: " + values);
        System.out.println("First: " + firstOrThrow(values));
    }

    //<T> — параметр типа. Метод работает с любым T
    static <T> T firstOrThrow(List<T> values){
        if(values.isEmpty())
            throw new NoSuchElementException("empty");
        return values.get(0);
    }

    static <T> void swap(List<T> values, int i, int j){
        T temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    }

    //<T, K> — два параметра: T элемент, K ключ
    static <T, K> Map<K, T> indexBy(List<T> values, Function<T, K> keyFunction){
        Map<K, T> index = new LinkedHashMap<>();
        for(T value : values){
            K key = keyFunction.apply(value);
            if(index.containsKey(key))
                throw new IllegalStateException("duplicate key: " + key);
            index.put(key, value);
        }
        return index;
    }
}