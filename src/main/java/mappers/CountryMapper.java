package mappers;

import model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper {

    public Country map(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setCode(resultSet.getString(1));
        country.setName(resultSet.getString(2));
        country.setContinent(resultSet.getString(3));
        country.setRegion(resultSet.getString(4));
        country.setPopulation(resultSet.getString(7));
        return country;
    }
}
