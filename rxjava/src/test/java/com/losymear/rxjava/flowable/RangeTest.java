package com.losymear.rxjava.flowable;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @program: rxjava
 * @description:
 * @author: losymear
 * @create: 2019-04-25 18:02
 */

@RunWith(JUnit4.class)
public class RangeTest {

    @Test
    public void test() {

        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);

    }

    @Test
    public void test2() {

        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);

    }
}
