package Guava;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @program: guavademo
 * @description:
 * @author: losymear
 * @create: 2018-10-22 11:41
 */

public class BasicUtilities {

    public static void main(String[] args) {
    }
    public static int checkBiggerThanZero(int a) {

        checkArgument(a>0,"Argument was %s but expected nonnegative", a);
//        checkArgument(a > 0);
        return a;
    }



}
