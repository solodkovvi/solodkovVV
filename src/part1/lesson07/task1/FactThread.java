package part1.lesson07.task1;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FactThread implements Runnable {
    /**
     * Переменная для сохранения результатов вычисления факториалов
     * key - чисто для которого ищем факториал
     * value - факториал этого числа
     */
    private static Map<Integer, BigInteger> cacheMap = new HashMap<Integer, BigInteger>();
    /**
     * число, для которого вычисляем факториал
     */
    private int calc = 0;
    /**
     * номер запускаемого потока
     */
    private int count = 0;

    /**
     * Конструктор для создания объекта
     * @param calc - число для которого вычисляем факториал
     * @param count - номер запускаемого потока
     */
    public FactThread(int calc, int count){
        this.calc = calc;
        this.count = count;
    }

    /**
     * Метод вычисления факториала.
     * Проверяем, есть ли факториал данного числа в map, если нет - записываем, попутно записывая каждую итерацию в map
     * @return возвращаем факториал числа
     */
    private BigInteger calcFact(){
        BigInteger fact;
        fact = BigInteger.valueOf(1);
        if (!(cacheMap.containsKey(calc))) {
            for (int i = 1; i <= calc; i++) {
                fact = fact.multiply(BigInteger.valueOf(i));
                cacheMap.put(i, fact);
            }
        }
        return cacheMap.get(calc);
    }

    /**
     * Метод работы потока. Вычисляем факториал и выводим информацию о нём.
     */
    @Override
    public void run() {
        System.out.println(count + " calc =" + this.calc + " fact ="+ calcFact().toString());
    }
}
