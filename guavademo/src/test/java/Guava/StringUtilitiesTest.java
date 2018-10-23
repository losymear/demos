package Guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @program: guavademo
 * @description: 测试字符串工具
 * @author: losymear
 * @create: 2018-10-25 13:52
 */


@RunWith(JUnit4.class)
public class StringUtilitiesTest {

    @Test
    public void testJoiner() {
        final Joiner joiner = Joiner.on("; ").skipNulls();
        assertEquals(joiner.join("Harry", null, "Ron", "Hermione"), "Harry; Ron; Hermione");
        assertEquals(Joiner.on(",").join(Arrays.asList(1, 5, 7)), "1,5,7");
    }

    @Test
    public void testSplitter() {
        assertEquals(Lists.newArrayList(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux")), Lists.newArrayList("foo", "bar", "qux"));

        System.out.println(Maps.newHashMap(ImmutableMap.of("x", 1, "y", 2, "z", 3)));
        assertThat(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings().withKeyValueSeparator(":")
                .split("x:1,y:2,z:3"), IsMapContaining.hasEntry("y", "2"));
    }


}
