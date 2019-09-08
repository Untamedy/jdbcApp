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
        String createFlatTablePath = "src\\main\\resources\\mysql\\createTable.txt";       
        String createStoreTabl = "src\\main\\resources\\mysql\\createTableStore.txt";
        String putDataFlat = "src\\main\\resources\\mysql\\flats.txt";
        String putDataAddress = "src\\main\\resources\\mysql\\address.txt";
        String punDataGoods = "src\\main\\resources\\mysql\\clients.txt";
        String punDataClient = "src\\main\\resources\\mysql\\goods.txt";

        PropertyReader propertyReader = new PropertyReader(propertyPath);
        ConnectionService connectionService = new ConnectionService(propertyReader);
        InputData init = new InputData(connectionService);
        //init.executeSQL(createFlatTablePath);        
        //init.executeSQL(putDataFlat);
        //init.executeSQL(putDataAddress);
        //init.executeSQL(putDataFlat);
        
        FilterService filterService = new FilterService(connectionService);
       List<Flat>  flats = filterService.selectByRegion("'west'");

    }

}
