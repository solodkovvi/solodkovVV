package part1.lesson14.task1;

import java.sql.Date;

/**
 * Класс, описывающий таблицу user в базе данных с  набором параметров и методов доступа к ним(get,set)
 */
public class User {
    private int id;
    private String name;
    private java.sql.Date birthday;
    private int loginID;
    private String city;
    private String email;
    private String description;

    /**
     * Конструктор для создания объекта класса User
     * @param id номер пользователя
     * @param name имя пользователя
     * @param birthday дата рождения пользователя
     * @param login_id логин пользователя
     * @param city город пользователя
     * @param email почта пользователя
     * @param description описание для пользователя
     */
    public User(int id, String name, Date birthday, int login_id, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.loginID = login_id;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", loginID=" + loginID +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
