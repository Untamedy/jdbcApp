package homework.store.services;

import homework.commonInit.ConnectionService;
import homework.store.entities.Client;
import homework.store.entities.Goods;
import homework.store.entities.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    private final Connection connection;
    private ClientService clientService;
    private GoodsService goodsService;
    private int code;
    private String curentCode = "Ord" + code;

    public OrderService(Connection connection) {
        this.connection = connection;
    }

    private final String addOrder = "insert into mydb.order (code,client) values(?,?)";
    private final String addGoodsToOrder = "insert into mydb.order_goods (ord_id, goods_id) values(?,?)";
    private final String selectOrderByCode = "select * from mydb.orders where code=?";

    public void addOrder(String clPhoneNum, List<Goods> orderList) throws SQLException {
        Client client = new ClientService(connection).isExists(clPhoneNum);
        ResultSet resultSet = null;
        PreparedStatement statement;
        if (null != client) {
            try {
                statement = connection.prepareStatement(addOrder);
                statement.setString(0, curentCode);
                statement.setInt(1, client.getId());
                resultSet = statement.executeQuery();
            } catch (SQLException ex) {
                LOGGER.warning(ex.getMessage());
            }
        } else {
            LOGGER.log(Level.INFO, "You should to create client with this phonenumber - {0}", clPhoneNum);
        }
        if (null != resultSet) {
            int orderId = resultSet.getInt("id");
            orderList.forEach((Goods g) -> {
                try {
                    addToOrderGoods(orderId, g);
                } catch (SQLException ex) {
                    Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    public void addToOrderGoods(int ordId, Goods goods) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(addGoodsToOrder);
        Goods g = new GoodsService(connection).isExists(goods.getArticul());
        if (null != g) {
            statement.setInt(0, ordId);
            statement.setInt(1, g.getId());
            statement.execute();
        } else {
            LOGGER.info("You should create goods with articul =" + goods.getArticul());
        }
    }

    public Order getOrder(String code) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(selectOrderByCode);
        statement.setObject(0, code);
        ResultSet resultSet = statement.executeQuery();
        Order order = new Order();
        order.setCode(resultSet.getString("code"));
        order.setCustomer(new ClientService(connection).getById(resultSet.getInt("client_id")));
        order.setGoods(new GoodsService(connection).getGoodsByOrdersId(resultSet.getInt("id")));
        return order;

    }

    private int generateCode() {
        code += 1;
        return code ;
    }

    public String getCurentCode() {
        return curentCode;
    }
    
    

}
