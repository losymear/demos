package com.losymear.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @program: concurrency
 * @description:
 *  示例来自https://www.baeldung.com/java-future
 * @author: losymear
 * @create: 2019-03-11 01:07
 */

public class SquareSum extends RecursiveTask<Integer> {

    private Integer n;

    public SquareSum(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        SquareSum calculator
                = new SquareSum(n - 1);

        calculator.fork();

        return n * n + calculator.join();
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        SquareSum calculator = new SquareSum(10);

        forkJoinPool.execute(calculator);
        System.out.println(calculator.compute());
       // 385，即1-10的平方和

    }
}
