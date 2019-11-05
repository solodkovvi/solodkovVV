package part1.lesson02.task01;
/**
*Задание 1. Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
*!Смоделировав ошибку «ArrayIndexOutOfBoundsException»
* @author SolodkovVV
 */
public class CallExceptionArrayIndexOutOfBounds {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        String s = "null";
        System.out.println(s.charAt(10));
    }
}
