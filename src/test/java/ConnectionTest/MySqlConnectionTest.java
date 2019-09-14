/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionTest;

import homework.commonInit.ConnectionService;
import homework.commonInit.PropertyReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class MySqlConnectionTest {
    
    private static ConnectionService conectionService;
    private static String path = "src\\main\\resources\\prop.properties";
    private static Connection connection;
    
    @BeforeClass
    public static void getConection(){
        PropertyReader reader = new PropertyReader(path);
        conectionService = new ConnectionService(reader);
               
    }
    
    @Test
    public void isConnected(){
        connection = conectionService.getConnection();
        assertNotNull(connection);        
    }
    
    @After
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
