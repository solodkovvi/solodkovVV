package part1.lesson02.task03;

/**
 * Задание 3. Дан массив объектов Person.
 * Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN),
 * name (имя - строка).
 * Создать два класса, методы которых будут реализовывать сортировку объектов.
 * Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:
 *         первые идут мужчины
 *         выше в списке тот, кто более старший
 *         имена сортируются по алфавиту
 *         Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 *         Предусмотреть генерацию исходного массива (10000 элементов и более).
 *         Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 * @author SolodkovVV
 */
public class SortPerson {

    private static int ARRAY_LENGTH = 10000;

    /**
     * Генерация массива {@link Person},
     * вывод неотсортированного массива {@link SortPerson#printArr(Person[])},
     * сортировка массива двумя способами {@link BubbleSortPerson} {@link InsertionSortPerson} по правилу интерфейса {@link ComparatorPerson#compare(Person, Person)}
     * вывод сортированного массива {@link SortPerson#printArr(Person[])}
     * подсчёт времени вывполнения массива
     */
    public static void main(String[] args) {
        //Создание основного массива Person для сортировки
        Person[] arrPerson = new Person[ARRAY_LENGTH];
        //Для корректного сравнения сортировок добавлена копия первого массива
        Person[] arrPersonCopy = new Person[ARRAY_LENGTH];
        //Заполнинение элементов массива
        for(int i = 0;i < arrPerson.length;i++){
            arrPerson[i] = new Person();
            arrPersonCopy[i] = new Person (arrPerson[i].getName(),arrPerson[i].getAge(),arrPerson[i].getSex());
        }

        //Сортировка и вывод первого массива
        System.out.println("Unsorted array one:");
        printArr(arrPerson);
        long timer1 = System.currentTimeMillis();
        (new InsertionSortPerson()).sort(arrPersonCopy);
        timer1 = System.currentTimeMillis()-timer1;
        System.out.println("Sorted array one:");
        printArr(arrPerson);

        //Сортировка и вывод второго массива
        System.out.println("Unsorted array two:");
        printArr(arrPersonCopy);
        long timer2 = System.currentTimeMillis();
        (new BubbleSortPerson()).sort(arrPersonCopy);
        //BubbleSortPerson.bubbleSort(arrPersonCopy);
        timer2 = System.currentTimeMillis()-timer2;
        System.out.println("Sorted array two:");
        printArr(arrPersonCopy);
        //Вывод на экран времени работы массивов в миллисекундах
        System.out.println("InsertionSort:"+timer1);
        System.out.println("BubbleSort:"+timer2);
    }

    //Функция печати массива
    private static void printArr(Person[] p){
        for (Person elPerson:p) {
            System.out.print(elPerson.toString()+";");
        }
        System.out.println();
    }

}
