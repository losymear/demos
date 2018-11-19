package com.losymear.log4j2.LoggerName_vs_ClassName;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * @program: log4j2
 * @description:
 * 代码来自官网 https://logging.apache.org/log4j/2.x/manual/usage.html#LoggerVsClass
 * 使用  LoggerName_vs_ClassName.yaml
 * VM参数 -Dlog4j.configurationFile=classpath:log4j2_parent_child.yaml
 * @author: losymear
 * @create: 2018-11-17 17:56
 */
public class App {

    public static void main( String[] args ) {
        Marker marker = MarkerManager.getMarker("CLASS");
        Child child = new Child();

        System.out.println("------- Parent Logger ----------");
        child.log(null);
        child.log(marker);
        child.logFromChild(null);
        child.logFromChild(marker);
        child.parentLog(null);
        child.parentLog(marker);

        child.setLogger(child.childLogger);

        System.out.println("------- Parent Logger set to Child Logger ----------");
        child.log(null);
        child.log(marker);
        child.logFromChild(null);
        child.logFromChild(marker);
    }
}

// 结果为
//------- Parent Logger ----------
//        1. Parent log message: Logger=com.losymear.log4j2.BasicUsage.Parent
//        2. Parent log message: Class=com.losymear.log4j2.BasicUsage.Parent
//        3. Log message from Child: Logger=com.losymear.log4j2.BasicUsage.Parent
//        4. Log message from Child: Class=com.losymear.log4j2.BasicUsage.Child
//        5. Parent logger, message from Child: Logger=com.losymear.log4j2.BasicUsage.Parent
//        6. Parent logger, message from Child: Class=com.losymear.log4j2.BasicUsage.Child
//        ------- Parent Logger set to Child Logger ----------
//        7. Parent log message: Logger=com.losymear.log4j2.BasicUsage.Child
//        8. Parent log message: Class=com.losymear.log4j2.BasicUsage.Parent
//        9. Log message from Child: Logger=com.losymear.log4j2.BasicUsage.Child
//        10. Log message from Child: Class=com.losymear.log4j2.BasicUsage.Child

