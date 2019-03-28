package com.losymear.stream.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;


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
        CopyOnWriteArrayList<Integer> numbers
                = new CopyOnWriteArrayList<>(new Integer[]{1, 3, 5, 8});

        Iterator<Integer> iterator = numbers.iterator();

        List<Integer> result = new LinkedList<>();
        iterator.forEachRemaining(result::add);

        numbers.add(10);
        assertThat(result).containsOnly(1, 3, 5, 8);




    }

}