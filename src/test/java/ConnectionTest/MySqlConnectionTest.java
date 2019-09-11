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
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class MySqlConnectionTest {
    
    private ConnectionService conectionService;
    private String path = "src\\main\\resources\\propmysql.properties";
    private Connection connection;
    
    @Before
    public void getConection(){
        PropertyReader reader = new PropertyReader(path);
        conectionService = new ConnectionService(reader);
               
    }
    
    @Test
    public void isConnected(){
        connection = conectionService.getConnection();
        assertTrue(connection!=null);        
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
