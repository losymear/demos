package com.losymear.rxjava;

import io.reactivex.Flowable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @program: rxjava
 * @description:
 * @author: losymear
 * @create: 2019-04-25 15:21
 */


@RunWith(JUnit4.class)
public class RxHelloWorld {

    @Test
    public void helloWorld() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

}
