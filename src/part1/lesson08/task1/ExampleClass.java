package part1.lesson08.task1;

import java.util.Objects;

/**
 * Класс для тестирования функционала
 */
public class ExampleClass {
    /**
     * Набор полей примитивов, строк и ссылочных объектов
     */
    String name;
    int age;
    String describe;
    ExampleSubClass subTwo;
    double weight;
    boolean flag;
    ExampleSubClass sub;

    /**
     * Пустой конструктор для инициализации
     */
    public ExampleClass() {
    }

    /**
     * Конструктор для объекта
     * @param name
     * @param age
     * @param describe
     * @param weight
     * @param flag
     */
    public ExampleClass(String name, int age, String describe, double weight, boolean flag) {
        this.name = name;
        this.age = age;
        this.describe = describe;
        this.weight = weight;
        this.flag = flag;
        this.sub = new ExampleSubClass(name + describe, age + 10);
        this.subTwo = new ExampleSubClass("sample", age + 10);
    }

    /**
     * Метод для вывода объекта в строку
     * @return все параметры в строку
     */
    @Override
    public String toString() {
        return "ExampleClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", describe='" + describe + '\'' +
                ", subTwo=" + subTwo +
                ", weight=" + weight +
                ", flag=" + flag +
                ", sub=" + sub +
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
        ExampleClass that = (ExampleClass) o;
        return age == that.age &&
                Double.compare(that.weight, weight) == 0 &&
                flag == that.flag &&
                Objects.equals(name, that.name) &&
                Objects.equals(describe, that.describe) &&
                Objects.equals(subTwo, that.subTwo) &&
                Objects.equals(sub, that.sub);
    }

}
