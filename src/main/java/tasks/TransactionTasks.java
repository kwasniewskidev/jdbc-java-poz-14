package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTasks {

    /**
     * Napisz program,
     * który na początku zwiększy populacje danego miasta o liczbę przekazaną w parametrze,
     * ale później wycofa tę transakcje.
     */
    public void updatePopulationWithRollback(Connection con, String population, String name) throws SQLException {
        String templateSql = "update city set population = ? where name = ?";
        con.setAutoCommit(false);
        PreparedStatement preparedStatement = con.prepareStatement(templateSql);
        preparedStatement.setString(1, population);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        con.rollback();
    }

    /**
     * Napisz program imitujący migrację ludności.
     * Niech program przyjmuje informacje o tym z jakiego oraz do jakiego kraju i ile ludzi chce się przenieść.
     * Uwaga, nie pozwól na dokonanie tej migracji w przypadku,
     * gdy populacja kraju po opuszczeniu ludzi byłaby mniejsza niż 1 tysiąc.
     */
    public void movePopulation(Connection con, String from, String to, String number) throws SQLException {
        con.setAutoCommit(false);
        String updateFrom = "update country set population = population - ? where name = ?";
        PreparedStatement preparedStatement = con.prepareStatement(updateFrom);
        preparedStatement.setString(1, number);
        preparedStatement.setString(2, from);
        preparedStatement.executeUpdate();

        String updateTo = "update country set population = population + ? where name = ?";
        preparedStatement = con.prepareStatement(updateTo);
        preparedStatement.setString(1, number);
        preparedStatement.setString(2, to);
        preparedStatement.executeUpdate();

        Long minPopulationInCountry = 1000L;
        String selectPopulation = "select population from country where name = ?";
        preparedStatement = con.prepareStatement(selectPopulation);
        preparedStatement.setString(1, from);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Long population = resultSet.getLong(1);
        if (population > minPopulationInCountry) {
            System.out.println("Migracja zakończona sukcesem");
            con.commit();
        } else {
            System.out.println("Migracja zakończona niepowodzeniem");
            con.rollback();
        }
    }


}
