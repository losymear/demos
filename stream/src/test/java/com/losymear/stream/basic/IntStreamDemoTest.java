package com.losymear.stream.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;


@RunWith(JUnit4.class)
public class IntStreamDemoTest {

    @Test
    public void test() {
        Optional<Integer> a = Optional.of(1);
        Optional<Integer> b = Optional.of(1);
        System.out.println(a == b);
    }

    @Test
    public void temp() {
    }

}