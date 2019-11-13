package part1.lesson03.task3;


import org.jetbrains.annotations.Contract;

import java.util.HashSet;
import java.util.Iterator;

/**
 Задание 3. Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 Необходимо сделать такую связь, правильно распределить поля и методы. Функциональность в целом должна сохраниться.
 При попытке положить Object в MathBox должно создаваться исключение.
 @author SolodkovVV
 */
public class MathBox<T extends Number> extends ObjectBox<T>{

    /**
     * Конструктор для создания колекции по внесенному массиву.
     * @param arrNum - массив с элементами, которые станут элементами коллекции
     */
    public MathBox(T[] arrNum){
        super(arrNum);
    }
    /**
     * Метод возвращающий сумму всех элементов коллекции
     * @return сумма всех элементов коллекции
     */
    public int summator() {
        int sum =0;
        Iterator<T> iterator = super.objectBox.iterator();
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
        Integer[] newMathBox = new Integer[super.objectBox.size()];
        int counter =0;
        for (Iterator<T> iterator = super.objectBox.iterator(); iterator.hasNext();){
            newMathBox[counter++]= iterator.next().intValue()/divider.intValue();
        }
        super.objectBox.clear();
        for (Integer num:newMathBox) {
            super.addObject((T)num);
        }
    }

    /**
     * Метод удаления элемента из коллекции
     * @param value - удаляемый элемент коллекции
     * @return - возвращает результат удаления элемента.
     * true - элемент удалён,
     * false - элемент отсутствовал в коллекции и не был удалён
     */
    public boolean deleteObject(Integer value){
        return super.deleteObject((T)value);
    }
    /**
     * Метод вывода элементов коллекции в строку
     * @return строка содержащая все элементы коллекции, перечисленные через запятую
     */
    @Override
    public String toString() {
        return super.toString();
    }
    /**
     * Метод получение хэш кода объекта по его значению
     * @return хэш код объекта
     */
    @Override
    public int hashCode() {
        return this.getUuid().hashCode();
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
        if (obj!=null && obj.getClass().equals(this.getClass()) && super.objectBox.containsAll(((ObjectBox)obj).objectBox)) {
            return this.toString().equals(obj.toString());
        }
            return super.objectBox == obj;
    }
}
