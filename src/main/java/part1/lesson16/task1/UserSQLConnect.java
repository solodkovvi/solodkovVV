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
 * Класс для работы с таблицей User
 */
public class UserSQLConnect {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static final String INSERT_USER = "INSERT INTO \"User\" " +
            "(name, birthday, \"login_ID\", city, email, description) VALUES (?,?,?,?,?,?) returning id";
    public static final String GET_BY_ID = "SELECT * FROM \"User\" " +
            " WHERE id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM  \"User\" " +
            " WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE \"User\" SET name = ?, birthday = ?, \"login_ID\" = ?, city = ?, email = ?, description = ? " +
            " WHERE id = ?";
    public static final String DELETE_BY_IDS = "DELETE FROM \"User\" WHERE id = ?";

    /**
     * Добавление пользователя в таблицу по объекту пользователю {@link User}
     * @param user объект пользоатель для создания в базе данных
     * @return возвращает сгенерированный id Объекта
     */
    public static int insertUser(User user){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(INSERT_USER);
            insertStmt.setString(1, user.getName());
            insertStmt.setDate(2, user.getBirthday());
            insertStmt.setInt(3,user.getLoginID());
            insertStmt.setString(4,user.getCity());
            insertStmt.setString(5,user.getEmail());
            insertStmt.setString(6,user.getDescription());
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
     * Метод получения пользователя по id из таблицы
     * @param id уникальный код из таблицы
     * @return возвращает пользователя
     */
    public static User getUserById(int id){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement selectStmt = connection.prepareStatement(GET_BY_ID);
            selectStmt.setInt(1, id);
            ResultSet resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                return user;
            }
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Удаляет пользователя из таблицы по Id
     * @param id уникальный код пользователя из таблицы
     * @return true - удаление успешно, false - ошибка
     */
    public static boolean deleteUserById(int id){
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
     * Изменение параметров пользователя
     * @param user ищем по user.id и меняем все параметры из объекта
     * @return true - изменено успешно, false - ошибка
     */
    public static boolean updateUser(User user){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(UPDATE_USER);
            insertStmt.setString(1, user.getName());
            insertStmt.setDate(2, user.getBirthday());
            insertStmt.setInt(3,user.getLoginID());
            insertStmt.setString(4,user.getCity());
            insertStmt.setString(5,user.getEmail());
            insertStmt.setString(6,user.getDescription());
            insertStmt.setInt(7,user.getId());
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
     * Удааление списка пользователй по массиву id. Использование batch
     * @param id список уникальных номеров пользователей
     * @return
     */
    public static boolean deleteUserByIds(int[] id){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(DELETE_BY_IDS);
            connection.setAutoCommit(false);
            for (int i = 0; i < id.length; i++) {
                insertStmt.setInt(1, id[i]);
                insertStmt.addBatch();
            }
            connection.commit();
            insertStmt.executeBatch();
            connection.commit();
            return true;
        }
        catch (SQLException e){
            logger.throwing(Level.ERROR, new Throwable(e.fillInStackTrace().getMessage()));
            e.printStackTrace();
            return false;
        }
    }
}
