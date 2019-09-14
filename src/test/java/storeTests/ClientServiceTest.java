package storeTests;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.commonInit.PropertyReader;
import homework.store.entities.Client;
import homework.store.services.ClientService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClientServiceTest extends Assert {

    private static ClientService clientService;
    private static ConnectionService connectionService;
    private static Connection conection;
    private static String path = "src\\main\\resources\\prop.properties";
    private static String sql = "src\\main\\resources\\createTable.txt";

    @BeforeClass
    public static void init() {
        PropertyReader reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);
        conection = connectionService.getConnection();
        clientService = new ClientService(conection);
        InputData input = new InputData(connectionService);
        try {
            input.executeSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void addClient() {
        try {
            clientService.addClient("Allan", "2056854");
            Client newClient = clientService.isExists("2056854");
            assertNotNull(newClient);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
