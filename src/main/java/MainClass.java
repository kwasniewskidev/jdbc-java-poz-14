import java.sql.*;
import java.util.Properties;

public class MainClass {

    public static void main(String... args) {
        runWithJDBC();
    }

    private static void runWithJDBC() {
        Connection con = null;
        try {
            con = estabilishConnection();

            //tutaj możemy odpalać taski
            TaskRunner taskRunner = new TaskRunner();
            taskRunner.runUpdatePopulationProcedureTask(con);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection estabilishConnection() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", PasswordGetter.get());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=GMT",
                properties);
    }

}
