package biteSize.utilities;


import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * This interface contains a default method that can be used anywhere a Properties
 * object is needed to be loaded.
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader {

    default public void createPropertiesObject() {
        Properties properties = new Properties();
    }

    default Properties loadProperties(String propertiesFilePath) {

        final Logger logger = LogManager.getLogger(this.getClass());

        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            logger.error("Could not read properties file", ioException);
        } catch (Exception exception) {
            logger.error("An error occurred in reading properties", exception);
        }
        return properties;
    }
}
