package com.losymear.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @program: concurrency
 * @description: : RecursiveActions need not be fully recursive,
 * so long as they maintain the basic divide-and-conquer approach.
 * 示例来自 https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/RecursiveAction.html
 * @author: losymear
 * @create: 2019-03-11 18:40
 */


public class Applyer extends RecursiveAction {
    final double[] array;
    final int lo, hi;
    double result;
    // keeps track of right-hand-side tasks
    Applyer next;

    Applyer(double[] array, int lo, int hi, Applyer next) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
        this.next = next;
    }

    double atLeaf(int l, int h) {
        double sum = 0;
        // perform leftmost base step
        for (int i = l; i < h; ++i) {
            sum += array[i] * array[i];
        }
        return sum;
    }

    protected void compute() {
        int l = lo;
        int h = hi;
        Applyer right = null;
        while (h - l > 1 && getSurplusQueuedTaskCount() <= 3) {
            int mid = (l + h) >>> 1;
            right = new Applyer(array, mid, h, right);
            right.fork();
            h = mid;
        }
        double sum = atLeaf(l, h);
        while (right != null) {
            // directly calculate if not stolen
            if (right.tryUnfork())
                sum += right.atLeaf(right.lo, right.hi);
            else {
                right.join();
                sum += right.result;
            }
            right = right.next;
        }
        result = sum;
    }

    public static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }

    public static void main(String[] args) {
        double result = Applyer.sumOfSquares(ForkJoinPool.commonPool(), new double[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(result);
    }
}
