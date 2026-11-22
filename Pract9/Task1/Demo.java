package Pract9.Task1;

import Pract9.Common.Runner;

public class Demo {
    public static void main(String[] args) throws Exception {
        int N = 8, M = 100_000;

        BrokenCounter b = new BrokenCounter();
        Runner.go(N, M, b::inc);
        System.out.println("broken: " + b.get() + " / " + (N * M));

        SafeCounter s = new SafeCounter();
        Runner.go(N, M, s::inc1);
        System.out.println("syncMethod: " + s.get1());

        Runner.go(N, M, s::inc2);
        System.out.println("syncBlock: " + s.get2());
    }
}
