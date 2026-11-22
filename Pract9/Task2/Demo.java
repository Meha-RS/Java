package Pract9.Task2;

public class Demo {
    public static void main(String[] args) throws Exception {
        BrokenWorker broken = new BrokenWorker();
        Thread t1 = new Thread(broken, "broken-worker");
        t1.start();
        Thread.sleep(200);
        broken.stop();
        t1.join(2000);
        System.out.println("broken: alive после stop? " + t1.isAlive());

        VolatileWorker vol = new VolatileWorker();
        Thread t2 = new Thread(vol, "volatile-worker");
        t2.start();
        Thread.sleep(200);
        vol.stop();
        t2.join(2000);
        System.out.println("volatile: alive после stop? " + t2.isAlive());
    }
}