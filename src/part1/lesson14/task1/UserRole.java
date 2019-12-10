package part1.lesson14.task1;
/**
 * Класс, описывающий таблицу user_role в базе данных с  набором параметров и методов доступа к ним(get,set)
 */
public class UserRole {
    private int id;
    private int userId;
    private int roleId;

    /**
     * Конструктор для создания объекта класса UserRole
     * @param id номер связи
     * @param userId код пользователя
     * @param roleId код роли
     */
    public UserRole(int id, int userId, int roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


}
