package homework.flats.services;

import homework.commonInit.ConnectionService;
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

    private ConnectionService connectionService;
    private final Connection connection = connectionService.getConnection();

    public FilterService() {
    }

    public List<Flat> selectBy(String selectBy, int min, int max) {
        List<Flat> flats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            List<ResultSet> results = (List<ResultSet>) statement.executeQuery("select * from Flat where" + selectBy + ">=" + min + " and " + selectBy + "<=" + max);
            flats = createFlats(results);
        } catch (SQLException ex) {
            Logger.getLogger(FilterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flats;
    }

    public List<Flat> selectByRegion(String region) {
        List<Flat> flats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            List<ResultSet> results = (List<ResultSet>) statement.executeQuery("select * from Flat where region=" + region);
            flats = createFlats(results);
        } catch (SQLException ex) {
            Logger.getLogger(FilterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flats;
    }

    public List<Flat> selectByAddress(String street, int build) {
        List<Flat> flats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Address where street=" + street + "and build=" + build);
            List<ResultSet> resultSets = (List<ResultSet>) statement.executeQuery("select * from Flat where addressId=" + resultSet.getInt("id"));
            flats = createFlats(resultSets);
        } catch (SQLException ex) {
            Logger.getLogger(FilterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flats;
    }

    public List<Flat> createFlats(List<ResultSet> resultSets) throws SQLException {
        List<Flat> flats = new ArrayList<>();
        resultSets.forEach((ResultSet r) -> {
            try {
                Flat flat = createNewFlat(r);
                flats.add(flat);
            } catch (SQLException ex) {
                Logger.getLogger(FilterService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        return flats;
    }

    public Flat createNewFlat(ResultSet resultSet) throws SQLException {
        Flat flat = new Flat();
        flat.setId(resultSet.getInt("id"));
        flat.setRegion(resultSet.getNString("region"));
        flat.setRooms(resultSet.getInt("room"));
        flat.setSqueare(resultSet.getInt("square"));
        flat.setPrice(resultSet.getDouble("price"));
        flat.setAddress(getAddress(resultSet.getInt("id")));
        return flat;
    }

    public Address getAddress(int id) {
        Statement statement;
        Address address = new Address();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from address where id=" + id);

            address.setStreet(resultSet.getString("street"));
            address.setBuildNum(resultSet.getInt("buildNum"));
        } catch (SQLException ex) {
            Logger.getLogger(FilterService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

}
