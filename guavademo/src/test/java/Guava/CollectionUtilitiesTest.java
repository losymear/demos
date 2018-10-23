package Guava;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @program: guavademo
 * @description:
 * @author: losymear
 * @create: 2018-10-24 13:38
 */
public class CollectionUtilitiesTest {

    @Test
    public void testMapsUniqueIndex() {
        HashSet<String> stringImmutableSet = Sets.newHashSet("a", "bd", "caa");

        Function function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        ImmutableMap immutableSet = Maps.uniqueIndex(stringImmutableSet, function);
        //  equivalent to   ImmutableMap immutableSet = Maps.uniqueIndex(stringImmutableSet, String::length);
        assertTrue(immutableSet.equals(ImmutableMap.of(1, "a", 2, "bd", 3, "caa")));
    }

    @Test
    public void testMapsDifference(){
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesDiffering(); // {"c" => (3, 4)}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}
    }
}
