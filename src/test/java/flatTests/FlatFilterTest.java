/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flatTests;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.commonInit.PropertyReader;
import homework.flats.entities.Flat;
import homework.flats.services.FilterService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class FlatFilterTest extends Assert {

    private ConnectionService connectionService;
    private Connection connection;
    private FilterService service;
    private final String path = "src\\main\\resources\\propmysql.properties";
    String createFlatTablePath = "src\\main\\resources\\mysql\\createTable.txt";   
    String putDataFlat = "src\\main\\resources\\mysql\\flats.txt";
    String putDataAddress = "src\\main\\resources\\mysql\\address.txt";

    @BeforeClass
    public void getConnection() {
        PropertyReader reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);
        connection = connectionService.getConnection();
        service = new FilterService();
        InputData input = new InputData(connectionService);
        try {
            input.executeSQL(createFlatTablePath);
            input.executeSQL(putDataAddress);
            input.executeSQL(putDataFlat);
                    
        } catch (SQLException ex) {
            Logger.getLogger(FlatFilterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

        
    @Test
    public void selectByRoomCount() {
        List<Flat> flats = service.selectBy(FilterService.Parameters.room, 1, 3);
        assertTrue(flats.size()==3);    
    }
    @Test
    public void selectBySquare() {
        List<Flat> flats = service.selectBy(FilterService.Parameters.square, 20, 40);
        assertTrue(flats.size()==3);    
    }
    @Test
    public void selectByPrice() {
        List<Flat> flats = service.selectBy(FilterService.Parameters.price, 10000, 30000);
        assertTrue(flats.size()==3);    
    }
    
    @Test
    public void selectByAddress() {
        List<Flat> flats = service.selectByAddress("First", 5);
        flats.forEach((Flat f)->{
           assertNotNull(f);
           assertTrue(f.getAddress().toString().equals("First 5"));  
        });           
    }
    
     @Test
    public void selectByregion(){
        List<Flat> flats = service.selectByRegion("west");
        flats.forEach((Flat f)->{
           assertNotNull(f);
           assertTrue(f.getRegion().equals("west")); 
        });           
    }
    
    
    
    @After
    public void closeConnect() throws SQLException{
        connection.close();
    }

}
