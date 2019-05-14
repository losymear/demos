package com.losymear.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * @program: rxjava
 * @description:
 * @author: losymear
 * @create: 2019-05-06 11:43
 */


public class Scrawl {


    public static void main(String[] args) {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);

    }

}
