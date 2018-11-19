package com.losymear.log4j2.LoggerName_vs_ClassName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

/**
 * @program: log4j2
 * @description:
 * @author: losymear
 * @create: 2018-11-17 17:55
 */
public class Child extends Parent {

    // The name of this Logge will be "org.apache.logging.Child"
    public Logger childLogger = LogManager.getLogger();

    public void childLog(Marker marker) {
        childLogger.debug(marker,"Child logger message");
    }

    public void logFromChild(Marker marker) {
        getLogger().debug(marker,"Log message from Child");
    }

    public void parentLog(Marker marker) {
        parentLogger.debug(marker,"Parent logger, message from Child");
    }
}

