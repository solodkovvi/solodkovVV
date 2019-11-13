package part1.lesson05.task1;

import part1.lesson02.task03.Person;

import java.util.Comparator;
import java.util.Random;
import java.util.UUID;

/**
 * У каждого животного есть
 *      уникальный идентификационный номер,
 *      кличка,
 *      хозяин (объект класс Person с полями – имя, возраст, пол) {@link Person},
 *      вес.
 */
public class Pet {
    /**
     * Уникальный идентификационный номер
     */
    private UUID uuid;
    /**
     * Кличка животного
     */
    private String name;
    /**
     * Хозяин для животного {@link Person}
     */
    private Person owner;
    /**
     * Вес животного
     */
    private double weight;

    /**
     * Конструктор с полным набором полей для создания. Уникальный идентиифкатор генерируется.
     * @param name - кличка животного
     * @param owner - хозяин животного {@link Person}
     * @param weight - вес животного
     */
    public Pet(String name, Person owner, double weight){
        uuid = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.weight = weight;
    }

    /**
     * Автоконструктор для создания животного по хозяину
     * @param owner - хозяин животного {@link Person}
     */
    public Pet(Person owner){
        uuid = UUID.randomUUID();
        this.name = PetName.values()[new Random().nextInt(PetName.values().length)].toString();
        this.owner = owner;
        this.weight = new Random().nextInt(10) + 1 + (new Random().nextInt(100)+1)/100.0;
    }

    /**
     * Метод получения значения уникального идентиифкатора животного
     * @return - уникальный идентификатор
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Метод получения клички животного
     * @return - кличка животного
     */
    public String getName() {
        return name;
    }

    /**
     * Метод получения веса животного
     * @return - вес животного
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Метод получения владельца животного {@link Person}
     * @return - владелец животного
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Метод изменения параметров животного
     * @param name - новая кличка животного. При null остаётся старая кличка
     * @param owner - новый хозяин животного. При null остаётся старый хозяин
     * @param weight - новый вес животного. При 0.0 остаётся старый вес
     */
    public void changePet(String name, Person owner, double weight){
        if (name!=null) {
            this.name = name;
        }
        if (owner != null){
            this.owner = owner;
        }
        if (weight!=0.0){
            this.weight = weight;
        }
    }

    /**
     * Метод вывода всех параметров в строку
     * @return - строка со всеми параметрами через запятую
     */
    @Override
    public String toString() {
        return "{owner:{" + this.owner.toString() + "} pet{" + this.name + ", " + this.weight + ", "+ this.uuid + "}}";
    }

    /**
     * Метод получения хеш кода по уникальному идентиифкатору животного
     * @return хэш код питомца
     */
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    /**
     * Метод сравнения равенства объектов
     * @param obj - объекит с которым сравниваем
     * @return - результат сравнения. true - равны, false - не равны
     */
    @Override
    public boolean equals(Object obj) {
        if (obj!=null && obj.getClass().equals(this.getClass())) {
            return this.toString().equals(obj.toString());
        }
        return this == obj;
    }
}
