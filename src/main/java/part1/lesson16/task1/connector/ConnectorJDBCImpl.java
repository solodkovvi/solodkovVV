package part1.lesson16.task1.connector;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part1.lesson16.task1.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Коннектор для создания единого подключения к базе
 */
public class ConnectorJDBCImpl implements ConnectorJDBC/*, AutoCloseable*/{
    private static final Logger logger = LogManager.getLogger(Main.class);
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
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
