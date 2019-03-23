package mappers;

import model.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper {

    public City map(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getString(1));
        city.setName(resultSet.getString(2));
        city.setCountryCode(resultSet.getString(3));
        city.setDistrict(resultSet.getString(4));
        city.setPopulation(resultSet.getString(5));
        return city;
    }
}
