package part1.lesson02.task03;

/**
 * дополнительный вариант сортировки. Из-за рекурсии на большом массиве вызываем stackOverflow
 */
public class QuickSortPerson  implements ComparatorPerson{

    private static Person[] pl = null;

    public static void quickSort(Person[] p) {
        pl = p;
        int startIndex = 0;
        int endIndex = pl.length - 1;
        doSort(startIndex, endIndex);
    }

    private static void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && ( ComparatorPerson.compare(pl[i],pl[cur])==1)) {
                i++;
            }
            while (j > cur && (ComparatorPerson.compare(pl[j],pl[cur])==0)) {
                j--;
            }
            if (i < j) {
                Person temp = pl[i];
                pl[i] = pl[j];
                pl[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }
}
