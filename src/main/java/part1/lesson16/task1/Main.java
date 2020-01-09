package part1.lesson16.task1;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import part1.lesson16.task1.connector.ConnectorJDBC;
import part1.lesson16.task1.connector.ConnectorJDBCImpl;

import java.sql.*;

import org.apache.logging.log4j.LogManager;

/**Взять за основу ДЗ_15,
 * покрыть код логированием
 * в основных блоках try покрыть уровнем INFO
 * с исключениях catch покрыть уровнем ERROR
 * настроить конфигурацию логера, что бы все логи записывались в БД, таблица LOGS,
 * колонки ID, DATE, LOG_LEVEL, MESSAGE, EXCEPTION
 * @author SolodkovVV
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static ConnectorJDBC connect = ConnectorJDBCImpl.getInstance();
    /**
     * Основной процесс разыгрывающий сценарии из задания
     * @param argv
     */
    public static void main(String[] argv) {
        logger.info("Начало процесса");
        System.out.println("Создаём роли-объекты");
        Role role = new Role(-1,"Administration", "Administration");
        Role role2 = new Role(-1,"Clients", "Clients");
        Role role3 = new Role(-1,"Billing", "Billing");
        System.out.println("Сохраняем роли в базу и присваиваем id Из базы в обхекты");
        RoleSQLConnect roleSQLConnect = new RoleSQLConnect(connect);
        role.setId(roleSQLConnect.insertRole(role));
        role2.setId(roleSQLConnect.insertRole(role2));
        role3.setId(roleSQLConnect.insertRole(role3));
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
        logger.info("Конец процесса");
    }

    /**
     * Метод использующий savepoint. Сохраняет только часть данных, указанных до точки сохранения
     * @param user пользователь для добавления в роли
     * @param user2 пользователь для добавления в роли
     * @param user3 пользователь для добавления в роли
     * @param role роль для прикрепления к пользователю
     * @param role2 роль для прикрепления к пользователю
     * @param role3 роль для прикрепления к пользователю
     */
    private static void rollbackSavepoint(User user, User user2, User user3, Role role, Role role2, Role role3){
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            logger.info("операция rollbackSavepoint");
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
        catch (SQLException err){
            logger.throwing(Level.ERROR, new Throwable(err.fillInStackTrace().getMessage()));
            err.printStackTrace();
        }
    }

    /**
     * Выборка по имени и логину
     * @param name имя для поиска
     * @param login_id логин для поиска
     * @return строка со всеми подходящими пользователями
     */
    private static String simpleSelect(String name, int login_id) {
        StringBuilder sb = new StringBuilder();
        try (Connection connection = ConnectorJDBCImpl.getInstance().getConnection()) {
            logger.info("операция simpleSelect");
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
        catch (SQLException err){
            logger.throwing(Level.ERROR, new Throwable(err.fillInStackTrace().getMessage()));
            err.printStackTrace();
        }
        return sb.toString();
    }

}
