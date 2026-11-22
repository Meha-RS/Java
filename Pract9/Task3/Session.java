package Pract9.Task3;

public class Session {
    private final String id;
    public Session(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    @Override public String toString() {
        return "Session(" + id + ")";
    }
}