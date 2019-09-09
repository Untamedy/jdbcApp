package homework.store.services;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import homework.commonInit.ConnectionService;
import homework.store.entities.Client;
import homework.store.entities.Goods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class GoodsService {

    private static final Logger LOGGER = Logger.getLogger(GoodsService.class.getName());

    private ConnectionService connectionService;
    private final Connection connection = connectionService.getConnection();

    String addGoods = "INSERT into Goods (name,articul) values (?,?) ";

    public void addGoods(String name, int articul) {
        Goods goods = isExists(articul);
        if (null == goods) {
            try {
                PreparedStatement statement = connection.prepareStatement(addGoods);
                statement.setObject(1, name);
                statement.setObject(2, articul);
                statement.execute();
            } catch (SQLException ex) {
                LOGGER.warning(ex.getMessage());
            }
        } else {
            LOGGER.log(Level.INFO, "Goods with this articul is alredy exists-> {0}", goods.toString());
        }
    }

    public Goods isExists(int articul) {
        Goods goods = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Goods where articul=" + articul);
            if (null != resultSet) {
                goods = new Goods();
                goods.setId(resultSet.getInt("id"));
                goods.setName(resultSet.getString("name"));
                goods.setArticul(resultSet.getInt(articul));
            }

        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return goods;
    }

    public List<Goods> getGoodsByOrdersId(int orderId) throws SQLException {
        List<Goods> goods = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Goods inner join Orders_Goods on Orders_Goods.order_id=" + orderId + " and Orders_Goods.goods_id = Goods.id");
        goods = createGoodsList(resultSet);
        return goods;
    }

    public Goods createGoods(ResultSet resultSet) throws SQLException {
        Goods goods = new Goods();
        goods.setArticul(resultSet.getInt("articul"));
        goods.setName(resultSet.getString("name"));
        return goods;
    }

    public List<Goods> createGoodsList(ResultSet resultSet) throws SQLException {
        List<Goods> goods = new ArrayList<>();
        while (resultSet.next()) {
            goods.add(createGoods(resultSet));
        }
        return goods;
    }

}
