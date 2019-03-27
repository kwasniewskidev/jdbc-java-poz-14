package tasks;

import java.sql.*;

public class MetadataTasks {

    /**
     * Za pomocą obiektu DatabaseMetaData możemy zdobyć informacje powiązane z bazą danych i połączeniem do bazy.
     */
    public void printMetaData(Connection co) throws SQLException {
        DatabaseMetaData metaData = co.getMetaData();
        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getTableTypes());
        System.out.println(metaData.getConnection());

        Statement statement = co.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CITY");
        resultSet.next();
        System.out.println(resultSet.getMetaData().getColumnLabel(2));

    }
}
