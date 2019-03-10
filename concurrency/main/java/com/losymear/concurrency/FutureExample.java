package com.losymear.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: concurrency
 * @description:
 * 来自https://www.baeldung.com/java-future
 * @author: losymear
 * @create: 2019-03-11 00:54
 */
public class FutureExample {
    public static class SquareCalculator {

        private ExecutorService executor
                = Executors.newSingleThreadExecutor();

        public Future<Integer> calculate(Integer input) {
            return executor.submit(() -> {
                Thread.sleep(1000);
                return input * input;
            });
        }
    }

    public static void main(String[] args) throws Exception {
        SquareCalculator squareCalculator = new SquareCalculator();

        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);
        squareCalculator.executor.shutdown();

    }


//    future1 is not done and future2 is not done
//    future1 is not done and future2 is not done
//    future1 is not done and future2 is not done
//    future1 is not done and future2 is not done
//    future1 is done and future2 is not done
//    future1 is done and future2 is not done
//    future1 is done and future2 is not done
//100 and 10000

}
