package tasks;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProceduresTask {

    /**
     * Wywołaj procedurę aktualizującą populacje danego miasta
     */
    public void updatePopulation(String name, int population, Connection con) throws SQLException {
        CallableStatement callableStatement = con.prepareCall("{CALL update_population(?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setLong(2, population);
        callableStatement.execute();
        callableStatement.close();
    }

}
