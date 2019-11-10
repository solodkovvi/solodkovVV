package part1.lesson03.task3;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Задание 2. Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 */
public class ObjectBox<T extends Object>{
    /**
     * Коллекция для хранения элементов класса.
     */
    protected HashSet<T> objectBox;
    /**
     * Конструктор для создания колекции по внесенному массиву.
     * @param arr - массив с элементами, которые станут элементами коллекции
     */
    public ObjectBox(T[] arr){
        objectBox = new HashSet<>();
        for (T obj:arr) {
            addObject(obj);
        }
    }
    /**
     * Пустой конструктор
     */
    public ObjectBox() {
    }
    /**
     * Метод добавления элемента в коллекцию
     * @param elem элемент для добавления в коллекцию
     * @return true - элемент успешно добавлен, false - элемент не был добавлен, т.к. уже присутствует в коллекции
     */
    public boolean addObject(T elem){
        return objectBox.add(elem);
    }
    /**
     * Метод удаления элемента из коллекцию
     * @param elem элемент для удаления из коллекции
     * @return true - элемент успешно добавлен, false - элемент не был добавлен, т.к. уже присутствует в коллекции
     */
    public boolean deleteObject(T elem){
        return objectBox.remove(elem);
    }
    /**
     * Метод печати элементов коллекции
     */
    public void dump(){
        System.out.println(this.toString());
    }
    /**
     * Метод вывода элементов коллекции в строку
     * @return строка содержащая все элементы коллекции, перечисленные через запятую
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        for(Iterator<T> iterator = objectBox.iterator();iterator.hasNext();){
            sb.append(iterator.next().toString());
            if (iterator.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

}
