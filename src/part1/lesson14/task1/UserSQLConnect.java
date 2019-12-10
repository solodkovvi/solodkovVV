package part1.lesson14.task1;

import part1.lesson14.task1.connector.ConnectorJDBCImpl;

import java.sql.*;

/**
 * Класс для работы с таблицей User
 */
public class UserSQLConnect {
    /**
     * Добавление пользователя в таблицу по объекту пользователю {@link User}
     * @param user объект пользоатель для создания в базе данных
     * @return возвращает сгенерированный id Объекта
     */
    public static int insertUser(User user){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO \"User\" " +
                    "(name, birthday, \"login_ID\", city, email, description) VALUES (?,?,?,?,?,?) returning id");
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
            PreparedStatement selectStmt = connection.prepareStatement("select * from \"User\" " +
                    " where id = ?");
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
            PreparedStatement insertStmt = connection.prepareStatement("DELETE from  \"User\" " +
                    " where id = ?");
            insertStmt.setInt(1, id);
            insertStmt.execute();
            return true;
        }
        catch (SQLException e){
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
            PreparedStatement insertStmt = connection.prepareStatement(
                    "UPDATE \"User\" set name = ?, birthday = ?, \"login_ID\" = ?, city = ?, email = ?, description = ? " +
                            "where id = ?");
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
            PreparedStatement insertStmt = connection.prepareStatement("DELETE from  \"User\" " +
                    " where id = ?");
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
            e.printStackTrace();
            return false;
        }
    }
}
