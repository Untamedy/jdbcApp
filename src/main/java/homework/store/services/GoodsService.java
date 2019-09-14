package homework.store.services;


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

    private final Connection connection;

    public GoodsService(Connection connection) {
        this.connection = connection;
    }
    
    

    String addGoods = "INSERT into mydb.goods (name,articul) values (?,?) ";

    public void addGoods(String name, int articul) {
        Goods goods = isExists(articul);
        if (null == goods) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement statement = connection.prepareStatement(addGoods);
                statement.setObject(1, name);
                statement.setObject(2, articul);
                statement.execute();
                connection.commit();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    LOGGER.warning(ex.getMessage());
                }
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
            ResultSet resultSet = statement.executeQuery("select * from mydb.goods where articul=" + articul);
            if (resultSet.next()) {
                goods = new Goods();
                goods.setId(resultSet.getInt("id"));
                goods.setName(resultSet.getString("name"));
                goods.setArticul(resultSet.getInt("articul"));
            }

        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return goods;
    }

    public List<Goods> getGoodsByOrdersId(int orderId) throws SQLException {
        List<Goods> goods = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from mydb.goods inner join mydb.orders_goods on mydb.orders_goods.order_id=" + orderId + " and mydb.orders_goods.goods_id = mydb.goods.id");
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
    
    public List<Goods> getGoodsSublist(int start, int end) throws SQLException{
        List<Goods> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from mydb.goods");
        while(resultSet.next()){
            list.add(createGoods(resultSet));            
            
        }
        if(end>list.size()){
            end = list.size();
        }
        List<Goods> sublist = list.subList(start, end);
        return sublist;
    }

}
