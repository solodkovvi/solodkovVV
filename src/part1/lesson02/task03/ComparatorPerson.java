package part1.lesson02.task03;

import java.util.Comparator;

/*Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:

        Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
        Предусмотреть генерацию исходного массива (10000 элементов и более).
        Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 */
public interface ComparatorPerson {
    /**
     * Правила сортировки: первые идут мужчины, выше в списке тот, кто более старший, имена сортируются по алфавиту
     * @param o1 - переменная Person, находящаяся левее в массиве
     * @param o2 - сравниваемая переменная Person, находящаяся правее в массиве
     * @return 1 - переменные находятся на правильных местах; 0 - необходимо поменять местами переменные
     * @exception IllegalArgumentException при сравнении дубликатов возникает ошибка. Для работы с большим массивом необходимо закомментировать, иначе будет срабатывать постоянно.
     */
    public static int compare(Person o1, Person o2) {
        if ((o1.getSex().equals("MAN") && o2.getSex().equals("WOMAN"))
                || (o1.getSex().equals(o2.getSex()) && o1.getAge()>o2.getAge())
                || (o1.getSex().equals(o2.getSex()) && o1.getAge()==o2.getAge() && o1.getName().compareTo(o2.getName())<0)
                ){
            return 1;
        } else if ((o1.getSex().equals(o2.getSex()) && o1.getAge()==o2.getAge() && o1.getName().equals(o2.getName()))) {
            return 1;
            //Для того, чтобы проверить дубликаты необходимо раскоментировать строку. В противном случае, на большом массиве всегда найдёт дубликат.
            //throw new IllegalArgumentException("dublicate");
        }

        return 0;
    }
}