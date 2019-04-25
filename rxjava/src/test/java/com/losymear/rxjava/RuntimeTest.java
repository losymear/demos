package com.losymear.rxjava;

import io.reactivex.Observable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @program: rxjava
 * @description:
 * @author: losymear
 * @create: 2019-04-25 16:18
 */

@RunWith(JUnit4.class)
public class RuntimeTest {
    @Test
    public void test() {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        })
                .subscribe(System.out::println, Throwable::printStackTrace);


    }

}
