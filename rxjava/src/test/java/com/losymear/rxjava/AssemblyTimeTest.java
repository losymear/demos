package com.losymear.rxjava;

import io.reactivex.Flowable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @program: rxjava
 * @description:
 * @author: losymear
 * @create: 2019-04-25 16:16
 */


@RunWith(JUnit4.class)
public class AssemblyTimeTest {

    @Test
    public void test() {
        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);
        System.out.println("this is assembly time");
        flow.subscribe(System.out::println);

    }

}
