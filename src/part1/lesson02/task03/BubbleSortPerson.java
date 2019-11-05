package part1.lesson02.task03;

public class BubbleSortPerson implements ComparatorPerson{
    /**
     * Сортировка массива Person методом "Пузырьковой сортировки"
     * @param p Сортируемый массив Person
     */
    public static void bubbleSort(Person[] p){
        for (int i=0;i<p.length-1;i++){
            for(int j=0;j<p.length-i-1;j++){
                if (ComparatorPerson.compare(p[j],p[j+1])==0){
                    Person temp = p[j];
                    p[j] = p[j+1];
                    p[j+1] = temp;
                }
            }
        }
    }
}
