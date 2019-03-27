package tasks;

import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BatchTasks {

    /**
     * dodaj kilka miast batchowo, czyli za pomocą jednego żądania do bazy danych
     */
    public void addBatch(Connection connection, List<City> cities) throws SQLException {
        connection.setAutoCommit(false);
        String templateSql = "insert into city (Name, CountryCode, District, Population) values (?,?,?,?)";
        PreparedStatement stmnst = connection.prepareStatement(templateSql);

        for (City city : cities) {
            stmnst.setString(1, city.getName());
            stmnst.setString(2, city.getCountryCode());
            stmnst.setString(3, city.getDistrict());
            stmnst.setString(4, city.getPopulation());
            stmnst.addBatch();
        }

        //wszystkie miasta
        int[] results = stmnst.executeBatch();
        if (results.length == cities.size()) {
            System.out.println("Poprawnie dodano wszystkie miasta");
        }
        connection.commit();
    }
}
