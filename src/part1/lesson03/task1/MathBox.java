package part1.lesson03.task1;


import org.jetbrains.annotations.Contract;

import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

/**
 * Задание 1. Написать класс MathBox, реализующий следующий функционал:
 *  Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 *  Существует метод summator, возвращающий сумму всех элементов коллекции.
 *  Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода.
 *  Хранящиеся в объекте данные полностью заменяются результатами деления.
 *  Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap).
 *  Выполнение контракта обязательно!
 *  Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 @author SolodkovVV
 */
public class MathBox<T extends Number> {
    /**
     * Коллекция для хранения элементов класса.
     */
    private HashSet<T> mathBox;
    /**
     * Уникальный идентификатор объекта
     */
    private UUID uuid;
    /**
     * Конструктор для создания колекции по внесенному массиву.
     * @param arrNum - массив с элементами, которые станут элементами коллекции
     */
    public MathBox(T[] arrNum){
        uuid = UUID.randomUUID();
        mathBox = new HashSet<>();
        for (T num:arrNum) {
            mathBox.add(num);
        }
    }

    /**
     * Метод возвращающий сумму всех элементов коллекции
     * @return сумма всех элементов коллекции
     */
    public int summator() {
        int sum =0;
        Iterator<T> iterator = mathBox.iterator();
        while (iterator.hasNext()){
            sum+=iterator.next().intValue();
        }
        return sum;
    }

    /**
     * Метод выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода.
     * Хранящиеся в объекте данные полностью заменяются результатами деления
     * @param divider - делитель для всех элементов коллекции
     */
    public void splitter(T divider){
        //Создаём массив для новых элементов коллекции.
        Integer[] newMathBox = new Integer[mathBox.size()];
        int counter =0;
        for (Iterator<T> iterator = mathBox.iterator(); iterator.hasNext();){
            newMathBox[counter++]= iterator.next().intValue()/divider.intValue();
        }
        //Чистим коллекцию
        mathBox.clear();
        //добавляем все элементы коллекции
        for (Integer num:newMathBox) {
            mathBox.add((T)num);
        }
    }

    /**
     * Метод печати всех элементов коллекции
     */
    public void print(){
        System.out.print("Start print MathBox:" );
        System.out.println(this.toString());
    }

    /**
     * Метод удаления элемента из коллекции
     * @param value - удаляемый элемент коллекции
     * @return - возвращает результат удаления элемента.
     * true - элемент удалён,
     * false - элемент отсутствовал в коллекции и не был удалён
     */
    public boolean clearValue(Integer value){
        return mathBox.remove(value);
    }

    /**
     * Метод вывода элементов коллекции в строку
     * @return строка содержащая все элементы коллекции, перечисленные через запятую
     */
    @Override
    public String toString() {
        return this.mathBox.toString();
    }

    /**
     * Метод получение хэш кода объекта по его значению
     * @return хэш код объекта
     */
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    /**
     * Метод проверки соответсвия объекта со сравниваемым объектом.
     * Вернёт true при условии полного совпадения типов и данных внутри
     * @param obj сравниваемый объект
     * @return true объекты равны, false - объекты не равны
     */
    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (obj!=null && obj.getClass().equals(this.getClass()) && this.mathBox.containsAll(((MathBox)obj).mathBox)) {
            return this.toString().equals(obj.toString());
        }
            return mathBox == obj;
    }
}
