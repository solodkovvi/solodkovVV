package part1.lesson16.task1;

/**
 * Класс, описывающий таблицу role в базе данных с  набором параметров и методов доступа к ним(get,set)
 */
public class Role {
    private int id;
    private String name;
    private String description;

    /**
     * Конструктор для создания объекта класса
     * @param id номер записи
     * @param name имя записи
     * @param description описание записи
     */
    public Role(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
