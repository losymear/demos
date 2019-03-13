package com.losymear.concurrency.completable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CompletableFuture;
import java.util.jar.JarEntry;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class CompletableFutureExampleTest {

    /**
     * concurrent不是事件循环。如果在单线程的node中，下列应该打印出 "start"，再"while"，再"finish"
     * 而本例可能先打印start，也可能先finish
     */
    @Test
    public void 测试异步效果() {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            int i = 0;
            while (i < 100) {
                i++;
            }
            System.out.println("finish");
            return "finish";
        });

        System.out.println("start");
        long startTime = System.currentTimeMillis();
        long currentTime;
        long period = 0L;
        while ((currentTime = System.currentTimeMillis()) < startTime + 4L * 1000) {
            if (currentTime - startTime > period * 1000) {
                System.out.println("while");
                period++;
            }
        }

    }

}