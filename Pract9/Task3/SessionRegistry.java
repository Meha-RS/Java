package Pract9.Task3;

import java.util.concurrent.ConcurrentHashMap;

public class SessionRegistry {
    private final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    public Session createIfAbsent(String id) {
        return sessions.computeIfAbsent(id, Session::new);
    }

    public Session get(String id) {
        return sessions.get(id);
    }
    public Session remove(String id) {
        return sessions.remove(id);
    }
    public int size() {
        return sessions.size();
    }
}