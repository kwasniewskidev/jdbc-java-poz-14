package tasks;

import mappers.CountryMapper;
import model.City;
import model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTasks {

    /**
     * Napisz program, który wyświetli populacją danego kraju w zależności od podanej nazwy kraju
     */
    public void printContry(Connection con, String name) throws SQLException {
        String templateSql = "Select * from country where name like ?";
        PreparedStatement preparedStatement = con.prepareStatement(templateSql);
        preparedStatement.setString(1, name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        CountryMapper countryMapper = new CountryMapper();
        Country country = countryMapper.map(resultSet);
        resultSet.close();
        System.out.println(country);
    }

    /**
     * Napisz program, który będzie komunikował się z użytkownikiem za pomocą konsoli i umożliwi mu dodanie nowego miasta. (edited)
     */
    public void insertCity(Connection con, City city) throws SQLException {
        String templateSql = "insert into city (Name, CountryCode, District, Population) values (?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(templateSql);
        preparedStatement.setString(1, city.getName());
        preparedStatement.setString(2, city.getCountryCode());
        preparedStatement.setString(3, city.getDistrict());
        preparedStatement.setString(4, city.getPopulation());
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        if (i == 1) {
            System.out.println("sukces");
        }
    }

    /**
     * Napisz, program umożliwiający użytkownik usunięcie danego miasta.
     */
    public void deleteCity(Connection con, String name) throws SQLException {
        String templateSql = "delete from city where name = ?";
        PreparedStatement preparedStatement = con.prepareStatement(templateSql);
        preparedStatement.setString(1, name);
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        if (i == 1) {
            System.out.println("usunieto");
        } else {
            System.out.println("nie znaleziono takiego rekordu");
        }
    }

}
