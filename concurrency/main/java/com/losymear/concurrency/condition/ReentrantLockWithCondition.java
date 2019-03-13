package com.losymear.concurrency.condition;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrency
 * @description:
 * @author: losymear
 * @create: 2019-03-13 19:12
 */

public class ReentrantLockWithCondition {

    Stack<String> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item) throws InterruptedException {
        try {
            lock.lock();
            while (stack.size() == CAPACITY) {
                stackFullCondition.await();
            }
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() throws InterruptedException {
        try {
            lock.lock();
            while (stack.size() == 0) {
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}

