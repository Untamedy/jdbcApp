package homework.store.services;

import homework.commonInit.ConnectionService;
import homework.store.entities.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class ClientService {
    
    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());

    private final Connection connection;

    String addClisent = "INSERT into mydb.clients (name,phoneNum) values (?,?) ";

   
        
    public ClientService(Connection connection) {
        this.connection = connection;
    }

    public void addClient(String name, String phoneNumber) {
        Client client = isExists(phoneNumber);
        if (null == client) {
            try {
                PreparedStatement statement = connection.prepareStatement(addClisent);
                statement.setObject(1, name);
                statement.setObject(2, phoneNumber);
                statement.execute();
            } catch (SQLException ex) {
                LOGGER.warning(ex.getMessage());
            }
        }else{
            LOGGER.log(Level.INFO, "Client is alredy exists-> {0}", client.toString());
        }
    }    
    

    public Client isExists(String phoneNumber) {
        Client client = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select* from mydb.clients where phoneNum=" + phoneNumber);
            if (null != resultSet) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPersonalNumber(resultSet.getString("phoneNum"));
            }

        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return client;
    }
    
    public Client getById(int id){
        Client client = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select* from mydb.clients where id=" + id);
            if (null != resultSet) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPersonalNumber(resultSet.getString("phoneNum"));
            }

        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return client;
        
        
    }

}
