package Guava.CompareAndOrder;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nullable;

/**
 * @program: guavademo
 * @description: For testing Ordering
 * @author: losymear
 * @create: 2018-10-23 17:07
 */
@Data
@AllArgsConstructor
public class OrderedFoo {
    @Nullable
    String sortedBy;
    int notSortedBy;
}
