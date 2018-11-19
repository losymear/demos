package com.losymear.log4j2.LoggerName_vs_ClassName;

/**
 * @program: log4j2
 * @description:
 * @author: losymear
 * @create: 2018-11-17 17:54
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

/**
 *
 */
public abstract class Parent {

    // The name of this Logger will be "org.apache.logging.Parent"
    protected static final Logger parentLogger = LogManager.getLogger();

    private Logger logger = parentLogger;

    protected Logger getLogger() {
        return logger;
    }

    protected void setLogger(Logger logger) {
        this.logger = logger;
    }


    public void log(Marker marker) {
        logger.debug(marker,"Parent log message");
    }
}
