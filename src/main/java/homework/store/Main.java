package homework.store;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.commonInit.PropertyReader;
import homework.flats.entities.Flat;
import homework.flats.services.FilterService;
import homework.store.entities.Goods;
import homework.store.entities.Order;
import homework.store.services.StoreService;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        //String propertyPathMySQL = "src\\main\\resources\\propmysql.properties";
        String propertyPathPostgreSQL = "src\\main\\resources\\prop.properties";
        

        PropertyReader propertyReader = new PropertyReader(propertyPathPostgreSQL);
        ConnectionService connectionService = new ConnectionService(propertyReader);
        InputData init = new InputData(connectionService);
        init.populateDB();

        StoreService storeService = new StoreService(connectionService);
        storeService.addGoods("Plum", 2323);
        storeService.addNewClient("Fruts", "096758434");

        List<Goods> orderList = storeService.getDataToOrder(1, 3);

        storeService.addOrder("096758434", orderList);
        Order order = storeService.getOrder("order_1359720187");
        System.out.println(order.toString());

        FilterService filterService = new FilterService(connectionService);
        List<Flat> flatsByRegion = filterService.selectByRegion("'west'");
        flatsByRegion.forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println("*********************");

        List<Flat> flatsByPrice = filterService.selectBy(FilterService.Parameters.price, 10000, 30000);
        flatsByPrice.forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println("********************");
        List<Flat> flatsByRoom = filterService.selectBy(FilterService.Parameters.room, 1, 3);
        flatsByRoom.forEach((f) -> {
            System.out.println(f.toString());
        });
        System.out.println("*********************");
        List<Flat> flatsBySqare = filterService.selectBy(FilterService.Parameters.square, 20, 40);
        flatsBySqare.forEach((f) -> {
            System.out.println(f.toString());
        });

    }

}
