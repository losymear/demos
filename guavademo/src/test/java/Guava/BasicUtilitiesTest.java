package Guava;

import Guava.CompareAndOrder.ComparedDay;
import Guava.CompareAndOrder.OrderedFoo;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.*;
import com.google.common.graph.MutableValueGraph;
import com.google.common.primitives.Ints;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(JUnit4.class)
public class BasicUtilitiesTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testOptional() {
        Optional<Integer> integerOptional = Optional.of(3);
        assertThat(integerOptional.isPresent(), is(true));
        assertThat(integerOptional.get(), equalTo(3));
        assertThat(Optional.absent().or(10), equalTo(10));
    }

    @Test
    public void testPreconditions() {
        // Expected exception
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Argument was" + " 0 " + "but expected nonnegative");
        BasicUtilities.checkBiggerThanZero(0);

    }

    @Test
    public void testComparisonChain() {
        assertEquals(new ComparedDay(2018, 11, 1).compareYear(new ComparedDay(2018, 9, 3)), 1);
        assertEquals(new ComparedDay(2018, 3, 1).compareYear(new ComparedDay(2018, 9, 3)), -1);
    }

    @Test
    public void testOrdering() {
        Ordering<OrderedFoo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<OrderedFoo, String>() {
            public String apply(OrderedFoo foo) {
                return foo.getSortedBy();
            }
        });

        assertEquals(ordering.min(new OrderedFoo("last", 0),
                new OrderedFoo("first", 0)).getSortedBy(), "first");
    }

}