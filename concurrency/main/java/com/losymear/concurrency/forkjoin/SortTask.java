package com.losymear.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * @program: concurrency
 * @description:
 * @author: losymear
 * @create: 2019-03-11 17:55
 */


public class SortTask extends RecursiveAction {
    final long[] array;
    final int lo, hi;

    public long[] getArray() {
        return array;
    }

    SortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    SortTask(long[] array) {
        this(array, 0, array.length);
    }

    protected void compute() {
        try {
            Thread.sleep(500);
        }catch (Exception e){
            System.out.println(e);
        }
        if (hi - lo < THRESHOLD)
            sortSequentially(lo, hi);
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid),
                    new SortTask(array, mid, hi));
            merge(lo, mid, hi);
        }

    }

    // implementation details follow:
    static final int THRESHOLD = 3;

    void sortSequentially(int lo, int hi) {
        Arrays.sort(array, lo, hi);
    }

    void merge(int lo, int mid, int hi) {
        long[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ?
                    buf[i++] : array[k++];
    }


    public static void main(String[] args) {
        SortTask sortTask = new SortTask(new long[]{8L, 2L, 3L, 4L, 5L, 6L, 7L});
        sortTask.compute();
        for (long item: sortTask.getArray()){
            System.out.print(item + " ,");
        }
    }
}
