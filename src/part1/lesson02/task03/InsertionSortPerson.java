package part1.lesson02.task03;

public class InsertionSortPerson implements ComparatorPerson{
    /**
     * Сортировка вставками.
     * @param p Сортируемый массив Person
     */
    public static void insertionSort(Person[] p){
        for (int left = 0; left < p.length; left++) {
            Person value = p[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (ComparatorPerson.compare(value, p[i])==1) {
                    p[i + 1] = p[i];
                } else {
                    break;
                }
            }
            p[i + 1] = value;
        }
    }

}
