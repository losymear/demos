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
 * @create: 2019-04-25 17:01
 */


@RunWith(JUnit4.class)
public class Demo1Test {
    @Test
    public void test() throws Exception {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish


    }

}
