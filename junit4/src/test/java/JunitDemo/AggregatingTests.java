package JunitDemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.sql.Time;

/**
 * @program: junit4
 * @description: 测试suite功能
 * @author: losymear
 * @create: 2018-10-19 15:47
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AssertTests.class,
        TimeoutTests.class
})
public class AggregatingTests {

}
