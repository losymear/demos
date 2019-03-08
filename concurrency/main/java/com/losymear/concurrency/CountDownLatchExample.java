package com.losymear.concurrency;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * @program: concurrency
 * @description: 测试CountDownLatch的用法
 * 该程序有两个CountDownLatch，startSignal是控制工作线程工作的开关，
 * doneSignal则是表示所有的工作线程完成了工作
 * <p>
 * 示例来自https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CountDownLatch.html
 * @author: losymear
 * @create: 2019-03-08 16:34
 */


class CountDownLatchExample {
    final static int N = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        // create and start threads
        for (int i = 0; i < N; ++i) {
            new Thread(new Worker(startSignal, doneSignal, i)).start();
        }

        // don't let run yet
        System.out.println("启动前");
        // let all threads proceed
        startSignal.countDown();
        // don't let run yet
        System.out.println("工作线程开始后... 开始等待" + "\n");

        doneSignal.await();           // wait for all to finish
        System.out.println("完成" + "\n");
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final Integer i;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal, Integer i) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        } // return;
    }

    void doWork() {
        try {

            Thread.sleep(2000);
            System.out.println("工作线程" + i + "工作完成..." + LocalDateTime.now().toLocalTime());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
}