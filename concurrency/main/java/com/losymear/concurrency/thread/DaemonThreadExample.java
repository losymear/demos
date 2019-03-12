package com.losymear.concurrency.thread;

/**
 * @program: concurrency
 * @description: daemon线程即使有死循环，只在user thread全部退出，那么JVM也会结束
 * @author: losymear
 * @create: 2019-03-12 18:56
 */


public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("daemon print");

                }
            }
        };
        daemonThread.setDaemon(true);


        daemonThread.start();
        System.out.println("main print");
    }


}
