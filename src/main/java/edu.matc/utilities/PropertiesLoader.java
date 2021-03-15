package edu.matc.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * This interface contains a default method that can be used anywhere a Properties
 * object is needed to be loaded.
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader {

    /**
     * This default method will load a properties file into a Properties instance
     * and return it.
     * @param propertiesFilePath a path to a file on the java classpath list
     * @return a populated Properties instance or an empty Properties instance if
     * the file path was not found.
     */
    default Properties loadProperties(String propertiesFilePath){
        Logger logger = LogManager.getLogger(this.getClass());
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            logger.info(ioException);
        } catch (Exception exception) {
            logger.info(exception);
        }
        return properties;
    }
}

