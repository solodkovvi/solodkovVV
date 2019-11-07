package part1.lesson02.task03;

public class InsertionSortPerson implements ComparatorPerson{
    /**
     * Функция сортировка массива Person методом "Сортировка вставками". {@link Person}
     * @param p Сортируемый массив Person
     */
    @Override
    public void sort(Person[] p) {
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
