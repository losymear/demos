package com.losymear.concurrency.threadlocal;

import com.google.common.collect.Lists;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: concurrency
 * @description: ThreadLocal<V>
 * 示例来自https://docs.oracle.com/javase/8/docs/api/java/lang/ThreadLocal.html
 * @author: losymear
 * @create: 2019-03-11 21:12
 */


public class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
}

