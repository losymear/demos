package com.losymear.stream.basic;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class StreamInterfaceDemoTest {

    @Test
    public void 测试flatMap() {
        Stream<Integer> stream = Lists.newArrayList(1, 2, 3).stream().flatMap(x -> Lists.newArrayList(x, x * 2).stream());

        System.out.println(stream.collect(Collectors.toList()));
    }


    @Test
    public void 测试of() {
        Assert.assertEquals(Stream.of(1, 2).collect(Collectors.toList()), Lists.newArrayList(1, 2));
    }


    @Test
    public void 测试limit() {
        Assert.assertEquals(Stream.of(1, 2).limit(3).collect(Collectors.toList()), Lists.newArrayList(1, 2));
        Assert.assertEquals(Stream.of(1, 2, 3, 4).limit(3).collect(Collectors.toList()), Lists.newArrayList(1, 2, 3));

    }

}