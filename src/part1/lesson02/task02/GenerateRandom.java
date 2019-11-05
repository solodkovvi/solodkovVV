package part1.lesson02.task02;

import java.util.Random;
import java.util.Scanner;

/**
 * Задание 2. Составить программу, генерирующую N случайных чисел. Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 * @author SolodkoVV
 */

public class GenerateRandom {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество генерируемых элементов N:");
        int n = in.nextInt();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int tmp = random.nextInt(100) - 1;
            if (tmp < 0) {
                    throw new ArithmeticException("Случайное число " + tmp + " меньше нуля. Ошибка произошла на итерации  " + i);
            }
            if(Math.pow((long)(Math.sqrt(tmp)),2)==i) {
                    System.out.println("K=" + i + " q=" + tmp);
            }
        }
    }
}
