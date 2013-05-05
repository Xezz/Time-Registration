package org.xezz.timeregistration.cleanup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * User: Xezz
 * Date: 04.05.13
 * Time: 21:51
 */
public class DriverCleanup implements javax.servlet.ServletContextListener {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    // On application shutdown
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("Inside contextDestroyed");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        for (; drivers.hasMoreElements();) {
            Driver driver = drivers.nextElement();
            // We search for driver that was loaded by this web application
            if (driver.getClass().getClassLoader() == this.getClass().getClassLoader()) {
                logger.info("found driver: " + driver.toString());
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.warn(e.getMessage());
                }
            }
        }
    }

    public void contextInitialized(ServletContextEvent event) {
        // Nothing to do here
    }
}
