package Pract7.Task3;

public class ResourceTracker implements AutoCloseable {
    private final String name;

    public ResourceTracker(String name) {
        this.name = name;
        System.out.println("Открыт ресурс: " + name);
    }

    public void doWork() throws Exception {
        System.out.println("Работаем с ресурсом: " + name);
    }

    @Override
    public void close() {
        System.out.println("ЗАКРЫТ ресурс: " + name);
    }
}