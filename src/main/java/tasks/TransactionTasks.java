package tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
