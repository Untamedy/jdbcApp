/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.commonInit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class PropertyReader {
    private static final Logger LOGGER = Logger.getLogger(PropertyReader.class.getName());

    private final Properties property = new Properties();
    private String path;

    public PropertyReader(String path) {
        this.path = path;
    }

    public Properties getProperties() {
        try {
            FileReader reader = new FileReader(path);
            property.load(reader);

        } catch (FileNotFoundException ex) {
            LOGGER.warning(ex.getMessage());
        } catch (IOException ex) {
           LOGGER.warning(ex.getMessage());
        }

        return property;
    }

}
