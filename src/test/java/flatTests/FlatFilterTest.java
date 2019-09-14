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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class FlatFilterTest extends Assert {

    private static ConnectionService connectionService;    
    private static FilterService service;
    private static String path = "src\\main\\resources\\prop.properties"; 
    
    

    @BeforeClass
    public static void getConnection() {
        PropertyReader reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);       
        service = new FilterService(connectionService);
        InputData input = new InputData(connectionService);
        try {
            input.populateDB();
        } catch (SQLException ex) {
            Logger.getLogger(FlatFilterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void selectByRoomCount() {                 
        List<Flat> flats = service.selectBy(FilterService.Parameters.room, 1, 3);
        flats.forEach((f)->{
            assertTrue(f.getRooms()<3&&f.getRooms()>1);
        });
        
    }

    @Test
    public void selectBySquare() {
        List<Flat> flats = service.selectBy(FilterService.Parameters.square, 20, 40);
        flats.forEach((f)->{
            assertTrue(f.getSqueare()<40&&f.getSqueare()>20);
        });
    }

    @Test
    public void selectByPrice() {
        List<Flat> flats = service.selectBy(FilterService.Parameters.price, 10000, 30000);
        flats.forEach((f)->{
            assertTrue(f.getPrice()<30000&&f.getPrice()>10000);
        });
    }

    @Test
    public void selectByAddress() {
        List<Flat> flats = service.selectByAddress("First", 5);
        flats.forEach((Flat f) -> {
            assertNotNull(f);
            assertTrue(f.getAddress().toString().equals("First_5"));
        });
    }

    @Test
    public void selectByregion() {
        List<Flat> flats = service.selectByRegion("west");
        flats.forEach((Flat f) -> {
            assertNotNull(f);
            assertTrue(f.getRegion().equals("west"));
        });
    }

    @AfterClass
    public static void closeConnect() throws SQLException {
   
    }

}
