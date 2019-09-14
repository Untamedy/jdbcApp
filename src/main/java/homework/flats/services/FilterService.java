package homework.flats.services;

import homework.commonInit.ConnectionService;
import homework.commonInit.InputData;
import homework.flats.entities.Address;
import homework.flats.entities.Flat;
import java.sql.Connection;
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
public class FilterService {
    private static final Logger LOGGER = Logger.getLogger(FilterService.class.getName());

    private ConnectionService connectionService;
    private Connection connection;

    public FilterService() {
    }

    public FilterService(ConnectionService connectionService) {
        this.connectionService = connectionService;
        this.connection = connectionService.getConnection();
    }

    public List<Flat> selectBy(Parameters parameter, int min, int max) {
        
        List<Flat> flats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from mydb.flats where " + parameter + " > " + min + " and " + parameter + " < " + max);
            flats = createFlats(results);

        } catch (SQLException ex) {
           LOGGER.warning(ex.getMessage());
        }
        return flats;
    }

    public List<Flat> selectByRegion(String region) {
        List<Flat> flats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from mydb.flats where region=" + region);
            flats = createFlats(results);
        } catch (SQLException ex) {
           LOGGER.warning(ex.getMessage());
        }
        return flats;
    }

    public List<Flat> selectByAddress(String street, int build) {
        List<Flat> flats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from mydb.address where street=" + street + "and build=" + build);
            ResultSet resultSets = statement.executeQuery("select * from mydb.flat where addressId=" + resultSet.getInt("id"));
            flats = createFlats(resultSets);
        } catch (SQLException ex) {
          LOGGER.warning(ex.getMessage());
        }
        return flats;
    }

    public List<Flat> createFlats(ResultSet resultSet) throws SQLException {
        List<Flat> flats = new ArrayList<>();
        while (resultSet.next()) {
            Flat flat = createNewFlat(resultSet);
            flats.add(flat);
        }
        return flats;
    }

    public Flat createNewFlat(ResultSet resultSet) throws SQLException {
        Flat flat = new Flat();
        flat.setId(resultSet.getInt("id"));
        flat.setRegion(resultSet.getString("region"));
        flat.setRooms(resultSet.getInt("room"));
        flat.setSqueare(resultSet.getInt("square"));
        flat.setPrice(resultSet.getDouble("price"));
        flat.setAddress(getAddress(resultSet.getInt("addressId")));

        return flat;
    }

    public Address getAddress(int id) {
        Statement statement;
        Address address = new Address();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from mydb.address where id=" + id);
            if (resultSet.next()) {
                address.setStreet(resultSet.getString("street"));
                address.setBuildNum(resultSet.getInt("buildNum"));
            }
        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return address;
    }

    private ResultSet hasNext(ResultSet resultSet) throws SQLException {
        ResultSet result = null;
        if (resultSet.next()) {
            result = resultSet;
            return result;
        }
        return result;
    }

    public static enum Parameters {
        price,
        square,
        room
    }

}
