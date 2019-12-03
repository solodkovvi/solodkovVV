package part1.lesson12.task1;

import java.util.*;

/**
 * Задание 1.     Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 * При этом объекты должны не только создаваться, но и периодически частично удаляться, чтобы GC имел возможность
 * очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 * Задание 2.     Доработать программу так, чтобы ошибка OutOfMemoryError
 * возникала в Metaspace /Permanent Generation
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        javaHeapSpaceError();
        OutOfMemoryError();

    }

    static void javaHeapSpaceError() throws InterruptedException {
        Thread.sleep(10_00);
        int multiplier = 10000;
        for (int i = 1; i < 50; i++) {
            Thread.sleep(300);
            System.out.println("Round " + i + " Free Memory: " + Runtime.getRuntime().freeMemory());
            int[] myIntList = new int[multiplier];
            for (int j = i; j > 1; j--) {
                myIntList[j] = i;
            }
            multiplier *= 2;
        }
    }

    static void OutOfMemoryError() {
        Map<Object, Object> map = new HashMap<>();
        for (int i=0;i>0;i++)
            map.put(i, i);
    }
}
