import java.util.*;

public class Task12{
    public static void main(String[] args){
        Repository<String, Course> repo = new InMemoryRepository<>();

        repo.save("CS101", new Course("CS101", "Intro"));
        repo.save("MA101", new Course("MA101", "Math"));
        repo.save("CS101", new Course("CS101", "Intro v2"));

        System.out.println("Find: " + repo.findById("CS101").orElseThrow());
        System.out.println("All: " + repo.findAll());
        System.out.println("Delete: " + repo.deleteById("MA101"));
        System.out.println("All: " + repo.findAll());
    }

    interface Repository<ID, T>{
        void save(ID id, T entity);
        Optional<T> findById(ID id);
        List<T> findAll();
        boolean deleteById(ID id);
    }

    static class InMemoryRepository<ID, T> implements Repository<ID, T>{
        private final Map<ID, T> store = new LinkedHashMap<>();

        @Override
        public void save(ID id, T entity){
            if(id == null || entity == null)
                throw new IllegalArgumentException("null нельзя");
            store.put(id, entity);
        }

        @Override
        public Optional<T> findById(ID id){
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<T> findAll(){
            return new ArrayList<>(store.values());
        }

        @Override
        public boolean deleteById(ID id){
            return store.remove(id) != null;
        }
    }
}
