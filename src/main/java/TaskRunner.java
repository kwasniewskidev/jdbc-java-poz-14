import model.City;
import tasks.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public void runPrintMetadataTask(Connection con) throws SQLException {
        MetadataTasks metadataTasks = new MetadataTasks();
        metadataTasks.printMetaData(con);
    }

    public void runMovePopulationTask(Connection con) throws SQLException {
        String from = "Aruba";
        String to = "Afghanistan";
        String number = "1000";
        TransactionTasks transactionTasks = new TransactionTasks();
        transactionTasks.movePopulation(con, from, to, number);
    }

    public void runAddCitisInBatchTask(Connection con) throws SQLException {
        List<City> cities = new ArrayList<City>();
        City city = new City();
        city.setName("Leszno");
        city.setCountryCode("Pol");
        city.setDistrict("Wielkopolskie");
        city.setPopulation("10000");
        cities.add(city);

        City city2 = new City();
        city2.setName("Kolo");
        city2.setCountryCode("Pol");
        city2.setDistrict("Wielkopolskie");
        city2.setPopulation("5000");
        cities.add(city2);

        BatchTasks batchTasks = new BatchTasks();
        batchTasks.addBatch(con, cities);


    }

    public void runUpdatePopulationProcedureTask(Connection con) throws SQLException {
        ProceduresTask proceduresTask = new ProceduresTask();
        proceduresTask.updatePopulation("Poznan", 1000, con);
    }

}
