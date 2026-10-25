import java.util.*;

public class Task13{
    public static void main(String[] args){
        Map<String, Set<String>> index = new HashMap<>();

        add(index, "CS101", List.of("prog", "base"));
        add(index, "MA101", List.of("math", "base"));
        add(index, "PH101", List.of("phys", "base"));
        System.out.println("Index: " + index);

        System.out.println("prog+base: " + intersect(index, List.of("prog", "base")));

        remove(index, "CS101");
        System.out.println("After remove: " + index);
    }

    static void add(Map<String, Set<String>> index, String courseId, List<String> tags){
        for(String tag : tags)
            index.computeIfAbsent(tag, k -> new HashSet<>()).add(courseId);
    }

    static void remove(Map<String, Set<String>> index, String courseId){
        Iterator<Map.Entry<String, Set<String>>> it = index.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Set<String>> e = it.next();
            e.getValue().remove(courseId);
            if(e.getValue().isEmpty())
                it.remove();
        }
    }

    static Set<String> intersect(Map<String, Set<String>> index, List<String> tags){
        Set<String> res = null;
        for(String tag : tags){
            Set<String> s = index.getOrDefault(tag, Set.of());
            if(res == null)
                res = new HashSet<>(s);
            else
                res.retainAll(s);
        }
        return res == null ? Set.of() : res;
    }
}
