package homework.store.services;

import homework.commonInit.ConnectionService;
import homework.store.entities.Client;
import homework.store.entities.Goods;
import homework.store.entities.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    private ConnectionService connectionService;
    private final Connection connection = connectionService.getConnection();
    private ClientService clientService;
    private GoodsService goodsService;

    private final String addOrder = "insert into Order (code,client) values(?,?)";
    private final String addGoodsToOrder = "insert into order_goods (ord_id, goods_id) values(?,?)";
    private final String selectOrderByCode = "select * from Orders where code=?";
   

    public void addOrder(String clPhoneNum, List<Goods> orderList) throws SQLException {
        Client client = new ClientService().isExists(clPhoneNum);
        ResultSet resultSet = null;
        PreparedStatement statement;
        if (null != client) {
            try {
                statement = connection.prepareStatement(addOrder);
                statement.setString(0, generateCode(client.getName()));
                statement.setInt(1, client.getId());
                resultSet = statement.executeQuery();
            } catch (SQLException ex) {
                LOGGER.warning(ex.getMessage());
            }
        } else {
            LOGGER.log(Level.INFO, "You should to create client with this phonenumber - {0}", clPhoneNum);
        }
        if(null!=resultSet){
            int orderId = resultSet.getInt("id");
            orderList.forEach((Goods g)->{
            try {
                addToOrderGoods(orderId,g);
            } catch (SQLException ex) {
                Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        }
    }
       

    public void addToOrderGoods(int ordId, Goods goods) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(addGoodsToOrder);
        Goods g = new GoodsService().isExists(goods.getArticul());
        if(null!=g){
         statement.setInt(0, ordId);
         statement.setInt(1, g.getId());
         statement.execute();            
        }else{
            LOGGER.info("You should create goods with articul ="+goods.getArticul());
        }
    }
    
    public Order getOrder(String code) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(selectOrderByCode);
        statement.setObject(0, code);
        ResultSet resultSet = statement.executeQuery();
        Order order = new Order();  
        order.setCode(resultSet.getString("code"));
        order.setCustomer(new ClientService().getById(resultSet.getInt("client_id")));
        order.setGoods(new GoodsService().getGoodsByOrdersId(resultSet.getInt("id")));        
        return order;
        
    }

    private String generateCode(String clname) {
        Date date = new Date(System.currentTimeMillis());
        return clname + date;
    }

}
