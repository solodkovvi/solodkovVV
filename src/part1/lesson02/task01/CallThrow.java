package part1.lesson02.task01;
/**
*Задание 1. Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
*!Вызвав свой вариант ошибки через оператор throw
 *  @author SolodkovVV
 */
public class CallThrow {
    /**
     * Вызов ошибки оператором throw. Ошибка NumberFormatException взята для примера.
     */
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        throw new NumberFormatException("any throw");
    }
}
