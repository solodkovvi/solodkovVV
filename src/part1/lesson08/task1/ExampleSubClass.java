package part1.lesson08.task1;

import java.util.Objects;

/**
 * Вложенный класс для примера работы сериализатора со ссылочными объектами
 */
public class ExampleSubClass {
    /**
     * параметры для примера работы
     */
    private String name;
    private int value;

    /**
     * Конструктор для заполнения параметров
     * @param name
     * @param value
     */
    public ExampleSubClass(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Конструктор для инициализации через рефлексию
     */
    public ExampleSubClass() {
    }

    /**
     * Метод вывода параметров в строку
     * @return параметры в строку
     */
    @Override
    public String toString() {
        return "ExampleSubClass{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
    /**
     * Метод сравнения параметров
     * @param o объект с которым сравниваем
     * @return результат сравнения. true - равны, false - не равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleSubClass that = (ExampleSubClass) o;
        return value == that.value &&
                Objects.equals(name, that.name);
    }

}
