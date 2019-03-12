package com.losymear.concurrency.parallelstream;

import com.losymear.concurrency.others.AssertUtils;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @program: concurrency
 * @description: parallel stream使用自定义的pool，而不是默认的ForkJoinPool.common
 * @author: losymear
 * @create: 2019-03-12 20:01
 */
public class CustomSteramPoolExample {
    public static void main(String[] args) throws Exception {

        long firstNum = 1;
        long lastNum = 2_000_000;

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());

        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        long actualTotal = customThreadPool.submit(
                () -> aList.parallelStream().reduce(0L, Long::sum)).get();

        AssertUtils.assertEquals((lastNum + firstNum) * lastNum / 2, actualTotal);

    }

}
