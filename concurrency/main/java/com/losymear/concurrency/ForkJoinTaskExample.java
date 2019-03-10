package com.losymear.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @program: concurrency
 * @description:
 *  示例来自https://www.baeldung.com/java-future
 * @author: losymear
 * @create: 2019-03-11 01:07
 */

public class ForkJoinTaskExample extends RecursiveTask<Integer> {

    private Integer n;

    public ForkJoinTaskExample(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        ForkJoinTaskExample calculator
                = new ForkJoinTaskExample(n - 1);

        calculator.fork();

        return n * n + calculator.join();
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTaskExample calculator = new ForkJoinTaskExample(10);

        forkJoinPool.execute(calculator);
        System.out.println(calculator.compute());
       // 385，即1-10的平方和

    }
}
