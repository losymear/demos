package com.losymear.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @program: concurrency
 * @description:
 * @author: losymear
 * @create: 2019-03-11 11:07
 */

public class RecursiveTaskExample extends RecursiveTask<Integer> {
    private int[] arr;

    private static final int THRESHOLD = 20;

    public RecursiveTaskExample(int[] arr) {
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(arr);
        }
    }

    private Collection<RecursiveTaskExample> createSubtasks() {
        List<RecursiveTaskExample> dividedTasks = new ArrayList<>();
        dividedTasks.add(new RecursiveTaskExample(
                Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new RecursiveTaskExample(
                Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }

    private Integer processing(int[] arr) {
        return Arrays.stream(arr)
                .filter(a -> a > 10 && a < 27)
                .map(a -> a * 10)
                .sum();
    }

    public static void main(String[] args) {
        RecursiveTaskExample task = new RecursiveTaskExample(new int[]{19, 2, 3, 21, 5, 6, 7});
        Integer result = task.compute();
        System.out.println(result);
        // 400
    }
}

