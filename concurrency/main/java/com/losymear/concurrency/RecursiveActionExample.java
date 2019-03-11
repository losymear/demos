package com.losymear.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

/**
 * @program: concurrency
 * @description: 如果workload长度小于THRESHOLD，直接打印；否则对半拆分成两个任务（递归），直到所有子任务的workload小于THRESHOLD
 * @author: losymear
 * @create: 2019-03-11 10:45
 */

public class RecursiveActionExample extends RecursiveAction {
    private String workload = "";
    private static final int THRESHOLD = 4;
    private static Logger logger = Logger.getAnonymousLogger();


    public RecursiveActionExample(String workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload.length() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            processing(workload);
        }
    }


    private List<RecursiveActionExample> createSubtasks() {
        List<RecursiveActionExample> subtasks = new ArrayList<>();
        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());
        subtasks.add(new RecursiveActionExample(partOne));
        subtasks.add(new RecursiveActionExample(partTwo));
        return subtasks;
    }

    private void processing(String work) {
        String result = work;
        logger.info("This result - (" + result + ") - was processed by "
                + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        RecursiveActionExample action1 = new RecursiveActionExample("abc");
        action1.compute();
        RecursiveActionExample action2 = new RecursiveActionExample("long action");
        action2.compute();

        // "act" "ng " "lo" "ion"

    }
}
