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
        String propertyPath = "C:\\Users\\YBolshakova\\Documents\\jdbcApp\\src\\main\\resources\\prop.properties";
        String createFlatTablePath = "C:\\Users\\YBolshakova\\Documents\\jdbcApp\\src\\main\\resources\\createTable.txt";       
        String createStoreTabl = "C:\\Users\\YBolshakova\\Documents\\jdbcApp\\src\\main\\resources\\createTableStore.txt";
        String putDataFlat = "C:\\Users\\YBolshakova\\Documents\\jdbcApp\\src\\main\\resources\\flats.txt";
        String putDataAddress = "C:\\Users\\YBolshakova\\Documents\\jdbcApp\\src\\main\\resources\\address.txt";
        String punDataGoods = "C:\\Users\\YBolshakova\\Documents\\jdbcApp\\src\\main\\resources\\clients.txt";
        String punDataClient = "C:\\Users\\YBolshakova\\Documents\\jdbcApp\\src\\main\\resources\\goods.txt";

        PropertyReader propertyReader = new PropertyReader(propertyPath);
        ConnectionService connectionService = new ConnectionService(propertyReader);
        InputData init = new InputData(connectionService);
        //init.executeSQL(createFlatTablePath);        
        //init.executeSQL(putDataFlat);
       // init.executeSQL(putDataAddress);
       // init.executeSQL(putDataFlat);
        
        FilterService filterService = new FilterService(connectionService);
       List<Flat>  flats = filterService.selectByRegion("'west'");

    }

}
