package part1.lesson03.task3;

public class TaskThree {
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
        System.out.println("Создали MathBox:");
        mb.dump();
        System.out.println("Сумматор:");
        System.out.println(mb.summator()+"");
        System.out.println("Разделили элементы на 2 и записали в MB, удалив старые:");
        mb.splitter(2);
        mb.dump();
        MathBox<Byte> mb2 = new MathBox(new Byte[]{0, 1, 2});
        System.out.println("Сравниваем два MB:");
        mb.dump();
        mb2.dump();
        System.out.println("Элементы equals? " + mb.equals(mb2));
        mb2.deleteObject(2);
        mb.addObject(11);
        System.out.println("Удалили во втором массиве элемент и записали в первый массив элемент:");
        mb.dump();
        mb2.dump();
    }
}
