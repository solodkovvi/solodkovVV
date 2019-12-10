package part1.lesson14.task1.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Коннектор для создания единого подключения к базе
 */
public class ConnectorJDBCImpl implements ConnectorJDBC/*, AutoCloseable*/{

    private static ConnectorJDBC connectionManager;

    private ConnectorJDBCImpl() {
    }

    /**
     * Возвращение единого коннекта
     * @return
     */
    public static ConnectorJDBC getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectorJDBCImpl();
        }
        return connectionManager;
    }

    /**
     * Метод для установления связи с базой.
     * @return
     */
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/docker",
                    "docker",
                    "docker");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
