import model.City;
import tasks.PreparedStatementTasks;
import tasks.TransactionTasks;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class TaskRunner {

    public void runUpdatePopulationWithRollbackTask(Connection con) throws SQLException {
        TransactionTasks transactionTasks = new TransactionTasks();
        transactionTasks.updatePopulationWithRollback(con, "1250", "Kabul");
    }

    public void runInsertCityTask(Connection con) throws SQLException {
        PreparedStatementTasks preparedStatementTasks = new PreparedStatementTasks();
        City city = new City();
        city.setName("Gniezno");
        city.setCountryCode("Pol");
        city.setDistrict("Wielkopolskie");
        city.setPopulation("20000");
        preparedStatementTasks.insertCity(con, city);

    }

    public void runDeleteCityTask(Connection con) throws SQLException {
        PreparedStatementTasks preparedStatementTasks = new PreparedStatementTasks();
        preparedStatementTasks.deleteCity(con, "Gniezno");
    }

    public void runPrintContryTask(Connection con) throws SQLException {
        PreparedStatementTasks preparedStatementTasks = new PreparedStatementTasks();
        System.out.println("Podaj kraj, który chcesz wyświetlić");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        preparedStatementTasks.printContry(con, name);
    }

}
