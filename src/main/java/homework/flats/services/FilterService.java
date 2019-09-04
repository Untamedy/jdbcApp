package homework.flats.services;

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
    private Connection connection = connectionService.getConnection();

    public FilterService() {
    }

    public List<Flat> selectBySquare(double min, double max) {
        List<Flat> flats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Flat where square>=min and square<=max");

            List<ResultSet> results = (List<ResultSet>) resultSet;
            for (ResultSet r : results) {
                Flat flat = new Flat();
                flat.setRegion(r.getNString("region"));
                flat.setRooms(r.getInt("room"));
                flat.setSqueare(r.getInt("square"));
                flat.setPrice(r.getDouble("price"));
                flat.setAddress(getAddress(r.getInt("id")));
                flats.add(flat);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FilterService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flats;

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
