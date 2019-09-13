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

    private GoodsService goodsService;
    private ConnectionService connectionService;
    private Connection connection;
    private String path = "src\\main\\resources\\prop.properties";
    private String sql = "src\\main\\resources\\createTable.txt";

    @BeforeClass
    public void init() {
        PropertyReader reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);
        connection = connectionService.getConnection();
        InputData input = new InputData(connectionService);
        try {
            input.executeSQL(sql);
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
