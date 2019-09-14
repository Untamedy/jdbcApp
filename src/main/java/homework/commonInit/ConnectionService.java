/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.commonInit;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class ConnectionService {
    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());
    
    private PropertyReader propReader;
    
    public ConnectionService(PropertyReader propReader){  
        this.propReader = propReader;
        
    }
    
    public Connection getConnection(){
      Properties property = propReader.getProperties();   
      Connection connection = null;
        try {
        connection = DriverManager.getConnection(property.getProperty("url"),property.getProperty("login"),property.getProperty("password"));
        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return connection;
      
    }
    
}
