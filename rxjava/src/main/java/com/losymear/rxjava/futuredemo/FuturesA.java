package com.losymear.rxjava.futuredemo;

/**
 * @program: rxjava
 * @description:
 * @author: losymear
 * @create: 2019-04-30 15:41
 * @see {https://gist.github.com/benjchristensen/4670979}
 */


import java.util.concurrent.*;

public class FuturesA {

    public static void run() throws Exception {
        ExecutorService executor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

        Future<String> f1 = executor.submit(new CallToRemoteServiceA());
        Future<String> f2 = executor.submit(new CallToRemoteServiceB());

        System.out.println(f1.get() + " - " + f2.get());
    }

    public static void main(String args[]) {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final class CallToRemoteServiceA implements Callable<String> {
        @Override
        public String call() throws Exception {
            // simulate fetching data from remote service
            Thread.sleep(100);
            return "responseA";
        }
    }

    private static final class CallToRemoteServiceB implements Callable<String> {
        @Override
        public String call() throws Exception {
            // simulate fetching data from remote service
            Thread.sleep(40);
            return "responseB";
        }
    }

}
