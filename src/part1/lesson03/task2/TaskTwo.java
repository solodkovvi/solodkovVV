package part1.lesson03.task2;


public class TaskTwo {
    /**
     * Создаём коллекцию от массива Number {@link ObjectBox}
     * Выводим элементы коллекции
     * Добавляем элемент в коллекцию
     * Удаляем элемент из коллекции
     * Печать элементов коллекции
     * @param args
     */
    public static void main(String[] args) {
        ObjectBox ob = new ObjectBox(new Number[]{1, 2, 3, 4});
        ob.dump();
        ob.addObject(10);
        ob.deleteObject(1);
        ob.dump();
    }
}
