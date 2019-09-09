package homework.store;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.commonInit.PropertyReader;
import homework.flats.entities.Flat;
import homework.flats.services.FilterService;
import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class Main {

    public static void main(String[] args) {
        String propertyPath = "src\\main\\resources\\propmysql.properties";
        //String propertyPath = "src\\main\\resources\\prop.properties";
        String createFlatTablePath = "src\\main\\resources\\mysql\\createTable.txt"; 
        //String createFlatTablePath = "src\\main\\resources\\createTable.txt";
        String createStoreTabl = "src\\main\\resources\\mysql\\createTableStore.txt";
        //String createStoreTabl = "src\\main\\resources\\createTableStore.txt";
         String putDataFlat = "src\\main\\resources\\mysql\\flats.txt";
        //String putDataFlat = "src\\main\\resources\\flats.txt";
        String putDataAddress = "src\\main\\resources\\mysql\\address.txt";
        //String putDataAddress = "src\\main\\resources\\address.txt";
        String punDataGoods = "src\\main\\resources\\mysql\\clients.txt";
        //String punDataGoods = "src\\main\\resources\\clients.txt";
         String punDataClient = "src\\main\\resources\\mysql\\goods.txt";
        //String punDataClient = "src\\main\\resources\\goods.txt";

        PropertyReader propertyReader = new PropertyReader(propertyPath);
        ConnectionService connectionService = new ConnectionService(propertyReader);
        InputData init = new InputData(connectionService);
        init.executeSQL(createFlatTablePath);  
     
        init.executeSQL(putDataAddress);
        init.executeSQL(putDataFlat);

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
