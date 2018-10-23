package Guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.awt.font.NumericShaper;
import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @program: guavademo
 * @description:
 * @author: losymear
 * @create: 2018-10-23 22:28
 */
public class CollectionTest {
    @Test
    public void testMultiset(){
        ImmutableMultiset multiset = ImmutableMultiset.of("a","b","a","b","c","d");
        assertTrue(multiset.count("a") == 2);
        // element not in Multiset
        assertTrue(multiset.count("non-esist") == 0);

    }

    @Test
    public void testRange(){
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
        assertTrue(rangeSet.asRanges().contains(Range.closed(1, 5)));
    }
    @Test
    public void tmp(){
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.open(1,10));
        rangeSet.add(Range.open(11,15).canonical(DiscreteDomain.integers()));
        System.out.println(rangeSet);

    }

}
