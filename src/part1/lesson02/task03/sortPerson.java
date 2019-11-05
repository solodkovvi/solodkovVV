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
public class sortPerson {

    private static int ARRAY_LENGTH = 10000;

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
        InsertionSortPerson.insertionSort(arrPerson);
        timer1 = System.currentTimeMillis()-timer1;
        System.out.println("Sorted array one:");
        printArr(arrPerson);

        //Сортировка и вывод второго массива
        System.out.println("Unsorted array two:");
        printArr(arrPersonCopy);
        long timer2 = System.currentTimeMillis();
        BubbleSortPerson.bubbleSort(arrPersonCopy);
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
