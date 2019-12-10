package part1.lesson14.task1.connector;

import java.sql.Connection;

/**
 * Интерфейс для коннектора
 */
public interface ConnectorJDBC {
    Connection getConnection();
}
