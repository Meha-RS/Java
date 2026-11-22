package Pract9.Common;

public class Runner {
    public static void go(int n, int m, Runnable r) throws InterruptedException {
        Thread[] t = new Thread[n];
        for (int i = 0; i < n; i++) {
            t[i] = new Thread(() -> {
                for (int k = 0; k < m; k++) r.run();
            });
            t[i].start();
        }
        for (Thread x : t) x.join();
    }
}
