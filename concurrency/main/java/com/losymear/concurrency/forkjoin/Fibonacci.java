package com.losymear.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @program: concurrency
 * @description:
 * @author: losymear
 * @create: 2019-03-11 19:05
 */


public class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci(10);
        System.out.println(fibonacci.compute());
    }
}
