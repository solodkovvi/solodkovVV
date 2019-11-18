package part1.lesson07.task1;

import java.util.Random;

/**
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива.
 * Использовать пул потоков для решения задачи.
 * Особенности выполнения:
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти,
 * очень вероятен StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 * По сути, есть несколько способа решения задания:
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет гораздо быстрее
 * @author SolodkovVV
 */
public class Main {
    final static int N = 1000;
    public static void main(String[] args) {
        Thread[] threads = new Thread[N];
        for (int i=0;i<N;i++){
            Runnable tmp = new FactThread(new Random().nextInt(10000)+1, i);
            threads[i] = new Thread(tmp);
        }
        long currTime = System.currentTimeMillis();
        for (Thread thread:threads){
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println((System.currentTimeMillis()-currTime) + "");
    }
}
