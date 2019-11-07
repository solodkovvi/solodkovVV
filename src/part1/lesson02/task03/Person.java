package part1.lesson02.task03;

import java.util.Random;

/**
 * Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN),
 * name (имя - строка).
 *
 *  Для формирования элементов реализован генератор случайных Person со своим набором правил для каждого поля.
 */
public class Person {

    /**
     * Возраст объекта Person
     */
    private int age;
    /**
     * Пол объекта Person {@link Sex}
     */
    private Sex sex;
    /**
     * Имя объекта Person
     */
    private String name;

    /**
     * автоконструктор с рандомным подбором параметров Person age, name, sex {@link Person#name} {@link Person#sex} {@link Person#age}
     */
    public Person() {
        Random rand = new Random();
        this.sex = new Sex();
        this.sex.setSex(rand.nextInt(2));
        this.setAge(rand.nextInt(100));
        this.setName(this.sex);
    }

    /**
     * конструктор с явным внесением параметров Person {@link Person#name} {@link Person#sex} {@link Person#age}
     * @param name - имя Person
     * @param age - возраст Person
     * @param sex - пол Person
     */
    public Person(String name, int age, String sex) {
        this.sex = new Sex();
        this.sex.setSex(sex);
        this.setAge(age);
        this.setName(name);
    }

    /**
     * Функция получения параметра возраст {@link Person#age}
     * @return int age
     */
    public int getAge() {
        return age;
    }

    /**
     * Функция задания параметра возраст {@link Person#age}
     * @param age - задаваемое значение age объекта Person
     */
    public void setAge(int age) {
        if (age>0 && age<=100) {
            this.age = age;
        } else this.age = 0;
    }

    /**
     * Функция получения строкового значения поля пол {@link Person#sex}
     * @return строковое значение Sex
     */
    public String getSex() {
        return sex.getSex();
    }

    /**
     * Функция задания параметра пол {@link Person#sex}
     * @param sex объект класса sex
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * Функция получения параметра имя {@link Person#name}
     * @return строковое значение name
     */
    public String getName() {
        return name;
    }

    /**
     * Функция случайной установки имени из наборов имён {@link ManName} {@link WomanName}
     * по параметру пол {@link Person#sex}
     * @param sex - значение пола для подбора случайного имени
     */
    public void setName(Sex sex) {
        if (this.getSex().equals("WOMAN")) {
            this.name = WomanName.values()[new Random().nextInt(WomanName.values().length)].toString();
        }
        else if (this.getSex().equals("MAN")) {
            this.name = ManName.values()[new Random().nextInt(ManName.values().length)].toString();
        }
    }

    /**
     * Функция задания параметра name {@link Person#name}
     * @param name строковое значение параметра name
     */
    public void setName(String name){
        this.name = name;
    }


    /**
     * Функция перевода параметров объекта Person в строку {@link Person#name} {@link Person#sex} {@link Person#age}
     * @return значения параметров Person через запятую
     */
    @Override
    public String toString() {
        return this.getSex()+","+this.getAge()+","+this.getName();
    }

}
