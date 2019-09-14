/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.store.services;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.store.entities.Goods;
import homework.store.entities.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class StoreService {

    private static final Logger LOGGER = Logger.getLogger(InputData.class.getName());

    private ClientService clientService;
    private GoodsService goodsService;
    private OrderService orderService;
    private ConnectionService connectionservice;
    private Connection connection;

    public StoreService() {
    }

    public StoreService(ConnectionService connectionservice) {
        this.connectionservice = connectionservice;
        this.connection = connectionservice.getConnection();
    }

    public void addNewClient(String name, String phoneNum) {
        clientService = new ClientService(connection);
        clientService.addClient(name, phoneNum);
    }

    public void addGoods(String name, int articul) {
        goodsService = new GoodsService(connection);
        goodsService.addGoods(name, articul);
    }

    public void addOrder(String clPhone, List<Goods> orderList) {
        orderService = new OrderService(connection);
        orderService.addOrder(clPhone, orderList);

    }

    public Order getOrder(String code) {
        orderService = new OrderService(connection);
        return orderService.getOrder(code);
    }

    public List<Goods> getDataToOrder(int start, int end) throws SQLException {
        List<Goods> sublistFortest = new GoodsService(connection).getGoodsSublist(start, end);
        return sublistFortest;

    }
}
