package part1.lesson16.task1;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part1.lesson16.task1.connector.ConnectorJDBC;
import part1.lesson16.task1.connector.ConnectorJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс для работы с таблицой Role
 */
public class RoleSQLConnect {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static final String SQL_INSERT_ROLE = "INSERT INTO role (name, description) VALUES (?,?) returning id";
    public static final String DELETE_ROLE = "DELETE from  role  where id = ?";
    public static final String UPDATE_ROLE_BY_ID = "UPDATE role set name = ?, description = ? where id = ?";
    public static final String GET_ROLE_BY_ID = "select * from role  where id = ?";
    public static final String DELETE_ALL = "DELETE from  role ";
    private ConnectorJDBC connector;

    public RoleSQLConnect(ConnectorJDBC connectorJDBC) {
        this.connector = connectorJDBC;
    }

    /**
     * Метод добавления роли в таблицу и получения уникального id из таблицы
     * @param role объект для удаления
     * @return возвращает уникальный номер добавленной записи
     */
    public int insertRole(Role role) {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(SQL_INSERT_ROLE);
            insertStmt.setString(1, role.getName());
            insertStmt.setString(2, role.getDescription());
            ResultSet resultSet = insertStmt.executeQuery();
            if (resultSet!=null && resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    /**
     * Метод удаления роли из таблицы
     * @param id
     * @return результат удаления true - успешно, false - ошибка при удалении
     */
    public static boolean deleteRole(int id) {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(DELETE_ROLE);
            insertStmt.setInt(1, id);
            insertStmt.execute();
            return true;
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Метод изменения Роли по id объекта
     * @param role
     * @return результат изменения true - успешно, false - ошибка при изменении
     */
    public static boolean updateRoleById(Role role) {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(UPDATE_ROLE_BY_ID);
            insertStmt.setString(1, role.getName());
            insertStmt.setString(2, role.getDescription());
            insertStmt.setInt(3, role.getId());
            insertStmt.execute();
            return true;
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Получить объект Role из таблцы, соответствующий id
     * @param id уникальный код для поиска
     * @return объект Role
     */
    public static Role getRoleById(int id) {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement selectStmt = connection.prepareStatement(GET_ROLE_BY_ID);
            selectStmt.setInt(1, id);
            ResultSet resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                return role;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Удаление всех записей из таблицы
     * @return результат удаления true - успешно, false - ошибка при удалении
     */
    public static boolean deleteAll() {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(DELETE_ALL);
            insertStmt.execute();
            return true;
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
            return false;
        }
    }
}
