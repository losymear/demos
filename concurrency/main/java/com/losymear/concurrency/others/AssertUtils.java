package com.losymear.concurrency.others;

import java.util.Objects;

/**
 * @program: concurrency
 * @description:
 * @author: losymear
 * @create: 2019-03-08 20:58
 */
public class AssertUtils {
    public static void assertEquals(Object a, Object b) {
        if (!Objects.equals(a,b)){
            throw new RuntimeException("不相等");
        }

    }
}
