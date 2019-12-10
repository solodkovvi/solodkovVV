package part1.lesson14.task1;


import part1.lesson14.task1.connector.ConnectorJDBCImpl;

import java.sql.*;

/**
 * 1)    Спроектировать базу
 * -      Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 * -      Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 * -      Таблица USER_ROLE содержит поля id, user_id, role_id
 *        Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
 * 2)      Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 * a)      Используя параметризированный запрос
 * b)      Используя batch процесс
 * 3)      Сделать параметризированную выборку по login_ID и name одновременно
 * 4)      Перевести connection в ручное управление транзакциями
 * a)      Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить логическую точку сохранения(SAVEPOINT)
 * б)   Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить точку сохранения (SAVEPOINT A), намеренно ввести некорректные данные
 * на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
 * @author SolodkovVV
 */
public class Main {
    /**
     * Основной процесс разыгрывающий сценарии из задания
     * @param argv
     * @throws SQLException
     */
    public static void main(String[] argv) throws SQLException {
        System.out.println("Создаём роли-объекты");
        Role role = new Role(-1,"Administration", "Administration");
        Role role2 = new Role(-1,"Clients", "Clients");
        Role role3 = new Role(-1,"Billing", "Billing");
        System.out.println("Сохраняем роли в базу и присваиваем id Из базы в обхекты");
        role.setId(RoleSQLConnect.insertRole(role));
        role2.setId(RoleSQLConnect.insertRole(role2));
        role3.setId(RoleSQLConnect.insertRole(role3));
        System.out.println("Выводим результат, который хранится в базе");
        System.out.println(RoleSQLConnect.getRoleById(role.getId()));
        System.out.println(RoleSQLConnect.getRoleById(role2.getId()));
        System.out.println(RoleSQLConnect.getRoleById(role3.getId()));
        System.out.println("Создаём пользователй-объекты");
        User user = new User(0,"Vasia",new Date(9999999), 1, "Tyumen", "tyumen@vasia.ru", "typical vasia");
        User user2 = new User(0,"Petia",new Date(9911999), 2, "Kazan", "kazan@petia.ru", "typical petia");
        User user3 = new User(0,"Mary",new Date(9911999), 1, "Kazan", "kazan@mary.ru", "simple mary");
        System.out.println("Сохраняем пользователей в базу и присваиваем id в объекты");
        user.setId(UserSQLConnect.insertUser(user));
        user2.setId(UserSQLConnect.insertUser(user2));
        user3.setId(UserSQLConnect.insertUser(user3));
        System.out.println("Выводим результат, который хранится в базе");
        System.out.println(user.toString());
        System.out.println(user2.toString());
        System.out.println(user3.toString());
        System.out.println("Параметризированная выборка");
        System.out.println(simpleSelect(user.getName(), user.getLoginID()));
        System.out.println("Используем savepoint");
        rollbackSavepoint(user, user2, user3, role, role2, role3);
        System.out.println("Чистим данные в таблицах. Используем Batch");
        RoleSQLConnect.deleteAll();
        UserSQLConnect.deleteUserByIds(new int[]{user.getId(), user2.getId(),user3.getId()});
        UserRoleSQLConnect.deleteAll();

    }

    /**
     * Метод использующий savepoint. Сохраняет только часть данных, указанных до точки сохранения
     * @param user пользователь для добавления в роли
     * @param user2 пользователь для добавления в роли
     * @param user3 пользователь для добавления в роли
     * @param role роль для прикрепления к пользователю
     * @param role2 роль для прикрепления к пользователю
     * @param role3 роль для прикрепления к пользователю
     * @throws SQLException
     */
    private static void rollbackSavepoint(User user, User user2, User user3, Role role, Role role2, Role role3) throws SQLException {
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO user_role " +
                    "(user_id, role_id)  values (?,?)");
            connection.setAutoCommit(false);
            insertStmt.setInt(1, user.getId());
            insertStmt.setInt(2, role.getId());
            insertStmt.executeUpdate();

            insertStmt.setInt(1, user2.getId());
            insertStmt.setInt(2, role.getId());
            insertStmt.executeUpdate();

            Savepoint savepoint = connection.setSavepoint("SAVEPOINT A");

            insertStmt.setInt(1, user3.getId());
            insertStmt.setInt(2, role3.getId());
            insertStmt.executeUpdate();

            connection.rollback(savepoint);

            connection.commit();
        }
    }

    /**
     * Выборка по имени и логину
     * @param name имя для поиска
     * @param login_id логин для поиска
     * @return строка со всеми подходящими пользователями
     * @throws SQLException
     */
    private static String simpleSelect(String name, int login_id) throws SQLException {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            PreparedStatement selectStmt = connection.prepareStatement("SELECT * from \"User\" " +
                    " where name = ? or \"login_ID\" = ?  ");
            selectStmt.setString(1, name);
            selectStmt.setInt(2, login_id);
            ResultSet resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                sb.append(user.toString()+"\n");
            }
        }
        return sb.toString();
    }

}
