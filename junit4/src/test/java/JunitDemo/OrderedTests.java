package JunitDemo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @program: junit4
 * @description: 测试顺序。。 不过iyvb的测试用例不应该有顺序依赖
 * @author: losymear
 * @create: 2018-10-19 16:09
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderedTests {
    @Test
    public void testA() {
        System.out.println("first");
    }
    @Test
    public void testB() {
        System.out.println("second");
    }
    @Test
    public void testC() {
        System.out.println("third");
    }
}
