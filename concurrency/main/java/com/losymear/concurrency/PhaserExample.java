package com.losymear.concurrency;

import com.losymear.concurrency.others.AssertUtils;
import com.losymear.concurrency.others.LongRunningAction;

import java.util.concurrent.*;

/**
 * @program: concurrency
 * @description:
 * @author: losymear
 * @create: 2019-03-08 20:58
 */
public class PhaserExample {

//    void runTasks(List<Runnable> tasks) {
//        final Phaser phaser = new Phaser(1); // "1" to register self
//        // create and start threads
//        for (final Runnable task : tasks) {
//            phaser.register();
//            new Thread() {
//                public void run() {
//                    phaser.arriveAndAwaitAdvance(); // await all creation
//                    task.run();
//                }
//            }.start();
//        }
//
//        // allow threads to start and deregister self
//        phaser.arriveAndDeregister();
//    }

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                1L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        Phaser ph = new Phaser(1);

        AssertUtils.assertEquals(0, ph.getPhase());


        executorService.submit(new LongRunningAction("thread-1", ph));
        executorService.submit(new LongRunningAction("thread-2", ph));
        executorService.submit(new LongRunningAction("thread-3", ph));

        ph.arriveAndAwaitAdvance();

        AssertUtils.assertEquals(1, ph.getPhase());


        executorService.submit(new LongRunningAction("thread-4", ph));
        executorService.submit(new LongRunningAction("thread-5", ph));
        ph.arriveAndAwaitAdvance();

        AssertUtils.assertEquals(2, ph.getPhase());
        ph.arriveAndAwaitAdvance();


        System.out.println(Thread.activeCount());


    }

}
