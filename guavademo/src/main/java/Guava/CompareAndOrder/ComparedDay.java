package Guava.CompareAndOrder;

import com.google.common.collect.ComparisonChain;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: guavademo
 * @description: A class to be compared
 * @author: losymear
 * @create: 2018-10-23 14:54
 */

@Data
@AllArgsConstructor
public class ComparedDay {
    private Integer year;
    private Integer month;
    private Integer day;

    public int compareYear(ComparedDay that) {
        return ComparisonChain.start()
                .compare(this.year, that.year)
                .compare(this.month, that.month)
                .compare(this.day, that.day)
                .result();
    }


}
