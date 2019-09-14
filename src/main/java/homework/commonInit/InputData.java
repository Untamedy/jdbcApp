package homework.commonInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class InputData {

    private static final Logger LOGGER = Logger.getLogger(InputData.class.getName());
    private ConnectionService connectionService;
    private Connection connection; 
    
    //private String createFlatPostgreSQL = "src\\main\\resources\\createTable.txt";
    //private String createStorePostgreSQL = "src\\main\\resources\\createTableStore.txt";
    //private String insertDataFlat = "src\\main\\resources\\insertData.txt";
    //private String insertDataStore = "src\\main\\resources\\insertStoreData.txt";
    
    
    private String createFlatMySQL = "src\\main\\resources\\mysql\\createTable.txt";
    private String createStoreMySQL= "src\\main\\resources\\mysql\\createTableStore.txt";
    private String insertDataFlat = "src\\main\\resources\\mysql\\inputDataFlat.txt";
    private String insertDataStore = "src\\main\\resources\\mysql\\inputDataStore.txt";
    

    public InputData() {
    }

    public InputData(ConnectionService connectionService) {
        this.connectionService = connectionService;
        connection= connectionService.getConnection();
    }

    public void populateDB() throws SQLException {
        executeSQL(createFlatMySQL);
        executeSQL(createStoreMySQL);
        Statement statemen = connection.createStatement();
        ResultSet result = statemen.executeQuery("select * from mydb.orders where code = 'order_test'");
        if (!result.next()) {
            try {
                executeSQL(insertDataFlat);
                executeSQL(insertDataStore);
            } catch (SQLException ex) {
                LOGGER.warning(ex.getMessage());
            }
        }
    }

    public void executeSQL(String path) throws SQLException {      

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            connection.setAutoCommit(false);
            String line = "";
            Statement statement = connection.createStatement();
            while ((line = reader.readLine()) != null) {
                statement.addBatch(line);
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException | IOException ex) {
            connection.rollback();
             LOGGER.warning(ex.getMessage());
        }
    }

}
