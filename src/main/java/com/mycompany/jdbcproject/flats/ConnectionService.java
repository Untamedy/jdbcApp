/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbcproject.flats;

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
    
    private static PropertyReader propReader;
    
    public ConnectionService(PropertyReader propReader){  
        this.propReader = propReader;
        
    }
    
    public static Connection getConnection(){
      Properties property = propReader.getProperties();
      Connection connection = null;  
        try {
            connection = DriverManager.getConnection(property.getProperty("url"), "login", "password");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
      
    }
    
}
