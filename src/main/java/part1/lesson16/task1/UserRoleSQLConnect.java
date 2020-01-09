package part1.lesson16.task1;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part1.lesson16.task1.connector.ConnectorJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс для подключения UserRole
 */
public class UserRoleSQLConnect {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static final String INSERT_USERROLE = "INSERT INTO user_role (user_id, role_id) VALUES (?,?) RETURNING id";
    public static final String GET_USERROLE_BY_ID = "SELECT * FROM user_role  WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM  user_role  WHERE id = ?";
    public static final String DELETE_ALL = "DELETE FROM  user_role ";

    /**
     * Добавление связи пользователь и роль в таблицу
     * @param userRole параметр для добавления в таблицу
     * @return id добавленной записи
     */
    public static int insertUserRole(UserRole userRole){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(INSERT_USERROLE);
            insertStmt.setInt(1, userRole.getUserId());
            insertStmt.setInt(2, userRole.getRoleId());
            ResultSet resultSet = insertStmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Получить строку из таблицы по id
     * @param id уникальный код строки
     * @return объект UserRole
     */
    public static UserRole getUserRoleById(int id){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement selectStmt = connection.prepareStatement(GET_USERROLE_BY_ID);
            selectStmt.setInt(1, id);
            ResultSet resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                UserRole userRole = new UserRole(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
                return userRole;
            }
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Удаление записи из таблицы по уникальному коду
     * @param id уникальный код записи
     * @return результат удаления true - успешно, false - ошибка при удалении
     */
    public static boolean deleteUserRoleById(int id){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(DELETE_BY_ID);
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
     * Удалить все записи из таблицы
     * @return результат удаления true - успешно, false - ошибка при удалении
     */
    public static boolean deleteAll(){
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
