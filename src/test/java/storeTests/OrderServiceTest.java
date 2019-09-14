/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storeTests;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.commonInit.PropertyReader;
import homework.store.entities.Goods;
import homework.store.entities.Order;
import homework.store.services.GoodsService;
import homework.store.services.OrderService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class OrderServiceTest extends Assert {

    private static OrderService orderService;
    private static GoodsService goodsService;
    private static ConnectionService connectionService;
    private static Connection connection;
    private static PropertyReader reader;
    private static List<Goods> goods = new ArrayList<>();
    private static String path = "src\\main\\resources\\prop.properties";
    private static  String sql = "src\\main\\resources\\createTable.txt";

    @BeforeClass
    public static void init() {
        reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);
        connection = connectionService.getConnection();
        orderService = new OrderService(connection);
        goodsService = new GoodsService(connection);
        InputData input = new InputData(connectionService);
        try {
            goods = goodsService.getGoodsSublist(1, 3);
            input.executeSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GoodsServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void addNewOrder() {
        try {
            List<Goods> list = goodsService.getGoodsSublist(1, 3);
            orderService = new OrderService(connection);
            orderService.addOrder("098765765", list);
            Order order = orderService.getOrder("order_4");
            assertNotNull(order);
        } catch (SQLException ex) {
            Logger.getLogger(OrderServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
