package homework.commonInit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class InputData {

    private static final Logger LOGGER = Logger.getLogger(InputData.class.getName());
    private ConnectionService connectionService;

    public InputData() {
    }

    public InputData(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public void executeSQL(String path) throws SQLException {
        Connection connection = connectionService.getConnection();        
        
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
            Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
