package Pract9.Task3;

import Pract9.Common.Runner;

public class Demo {
    public static void main(String[] args) throws Exception {
        SessionRegistry registry = new SessionRegistry();
        int N = 8;
        int IDS = 100;

        Runner.go(N, IDS, () -> {
            for (int id = 0; id < IDS; id++) {
                registry.createIfAbsent("session-" + id);
            }
        });

        System.out.println("size = " + registry.size() + " (ждали " + IDS + ")");
    }
}