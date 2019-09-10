/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.store.services;

import homework.commonInit.ConnectionService;
import homework.store.entities.Goods;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class StoreService {
    
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
    
    
    public void addNewClient(String name, String phoneNum){
        clientService = new ClientService(connection);        
        clientService.addClient(name,phoneNum);
    }
    
    public void addGoods(String name, int articul){
        goodsService = new GoodsService(connection);
        goodsService.addGoods(name, articul);        
    }
    
    public void addOrder(String clPhone, List<Goods> orderList) throws SQLException{
        orderService = new OrderService(connection);        
        try {
            orderService.addOrder(clPhone, orderList);
        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }  
    
    public List<Goods> getDataToOrder(int start, int end) throws SQLException{
        List<Goods> sublistFortest = new GoodsService(connection).getGoodsSublist(start, end);
        return sublistFortest;
        
        
    }
}