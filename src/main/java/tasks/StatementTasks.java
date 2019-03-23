package tasks;

import mappers.CountryMapper;
import model.Country;

import java.sql.*;

public class StatementTasks {

    /**
     * Napisz program, który wyświetla kraj o największej populacji.
     */
    public void getTheBiggetsPopulation(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from country order by population desc limit 1");
        resultSet.next();
        String country = resultSet.getString("Name");
        System.out.println("Kraj o największej populacji to " + country);
        con.close();
        resultSet.close();
    }

    /**
     * Napisz program, który wylistuje wszystkie kraje w odwrotnej kolejności alfabetycznej.
     */
    public void getAllCountriesDesc(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from country order by name desc");
        while (resultSet.next()) {
            String country = resultSet.getString("Name");
            System.out.println(country);
        }
        resultSet.close();
        con.close();
    }

    /**
     * Stwórz klasy (modelowe), które będą odwzorowaniem encji tabel
     * CITY oraz COUNTRY oraz klasy odpowiedzialne za zasilanie (mapujące) klas modelowych danymi.
     * Niech parametrem metody mapującej będzie obiekt ResultSet,
     * a wynikiem klasa modelowa.*Nadpisz metodę toString() klas modelowych,
     * tak aby wyświetlały one dane w przejrzysty sposób.
     */
    public void printChina(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from country where name = 'China'");
        resultSet.next();
        CountryMapper countryMapper = new CountryMapper();
        Country country = countryMapper.map(resultSet);
        System.out.println(country);
        statement.close();
    }

    /**
     * Napisz program za pomocą, którego dodasz kolejne miasto do tabeli CITY
     */
    public void insert(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        Integer i = statement.executeUpdate("insert into city (Name, CountryCode, District, Population) values ('Swarzedz','POL','Wielkopolskie',20000)");
        if (i == 1) {
            System.out.println("success");
        }
        statement.close();
    }


}
