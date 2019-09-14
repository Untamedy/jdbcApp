package storeTests;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.commonInit.PropertyReader;
import homework.store.entities.Goods;
import homework.store.services.GoodsService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author YBolshakova
 */
public class GoodsServiceTest extends Assert {

    private static GoodsService goodsService;
    private static ConnectionService connectionService;
    private static Connection connection;
    //private static String path = "src\\main\\resources\\prop.properties";
    private static String path = "src\\main\\resources\\propmysql.properties";
    

    @BeforeClass
    public static void init() {
        PropertyReader reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);
        connection = connectionService.getConnection();
        goodsService = new GoodsService(connection);
        InputData input = new InputData(connectionService);
        try {
            input.populateDB();
        } catch (SQLException ex) {
            Logger.getLogger(GoodsServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void addGoods() {
        try {
            goodsService.addGoods("Bananas", 2056854);
            Goods newGoods = goodsService.isExists(2056854);
            assertNotNull(newGoods);
        } catch (SQLException ex) {
            Logger.getLogger(GoodsServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void addAlredyExistsGoods() {        
        try {
            goodsService.addGoods("Toys", 2056854);
            Goods goods = goodsService.isExists(2056854);
             assertTrue(!goods.getName().equals("Toys"));
        } catch (SQLException ex) {
            Logger.getLogger(GoodsServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
