package part1.lesson14.task1;

import part1.lesson14.task1.connector.ConnectorJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс для работы с таблицой Role
 */
public class RoleSQLConnect {
    /**
     * Метод добавления роли в таблицу и получения уникального id из таблицы
     * @param role объект для удаления
     * @return возвращает уникальный номер добавленной записи
     */
    public static int insertRole(Role role) {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO role " +
                    "(name, description) VALUES (?,?) returning id");
            insertStmt.setString(1, role.getName());
            insertStmt.setString(2, role.getDescription());
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
     * Метод удаления роли из таблицы
     * @param id
     * @return результат удаления true - успешно, false - ошибка при удалении
     */
    public static boolean deleteRole(int id) {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement("DELETE from  role " +
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
     * Метод изменения Роли по id объекта
     * @param role
     * @return результат изменения true - успешно, false - ошибка при изменении
     */
    public static boolean updateRoleById(Role role) {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement(
                    "UPDATE role set name = ?, description = ? where id = ?");
            insertStmt.setString(1, role.getName());
            insertStmt.setString(2, role.getDescription());
            insertStmt.setInt(3, role.getId());
            insertStmt.execute();
            return true;
        }
        catch (SQLException e){
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
            PreparedStatement selectStmt = connection.prepareStatement("select * from role " +
                    " where id = ?");
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
            PreparedStatement insertStmt = connection.prepareStatement("DELETE from  role ");
            insertStmt.execute();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
