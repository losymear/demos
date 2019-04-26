package com.losymear.rxjava;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: rxjava
 * @description:
 * @author: losymear
 * @create: 2019-04-25 18:59
 */

@RunWith(JUnit4.class)
public class DefferedDependentTest {
    @Test
    public void test() {
        AtomicInteger count = new AtomicInteger();

        Observable.range(1, 10)
                .doOnNext(ignored -> count.incrementAndGet())
                .ignoreElements()
                .andThen(Single.just(count.get()))
                .subscribe(System.out::println);
    }

    @Test
    public void test2() {
        AtomicInteger count = new AtomicInteger();

        Observable.range(1, 10)
                .doOnNext(ignored -> count.incrementAndGet())
                .ignoreElements()
                .andThen(Single.defer(() -> Single.just(count.get())))
                .subscribe(System.out::println);
    }

}
