package part1.lesson03.task1;

public class TaskOne {
    /**
     * Создаём коллекцию от массива Number {@link MathBox}
     * Выводим сумму элементов коллекции  и сами элементы
     * Делим элементы на 2 и выводим полученный в коллекции результат
     * Создаём коллекцию от массива Byte
     * Сравниваем коллекции. Не смотря на одинаковые примитивы внутри, они принадлежат разным классам Integer и Byte. поэтому результат false
     * Удаляем элемент из коллекции и выводим результат
     * @param args
     */
    public static void main(String[] args) {
        MathBox<Number> mb = new MathBox(new Number[]{1,2,3,4});
        System.out.println(mb.summator()+"");
        mb.print();
        System.out.println(mb.hashCode()+"");
        mb.splitter(2);
        mb.print();
        MathBox<Number> mb2 = new MathBox(new Number[]{1,2,3,4});
        System.out.println(mb.hashCode()+"");
        System.out.println(mb.equals(mb2));
        mb2.clearValue(2);
        mb2.print();
    }
}
