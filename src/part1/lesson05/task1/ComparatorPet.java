package part1.lesson05.task1;

import java.util.Comparator;

public class ComparatorPet implements Comparator<Pet> {
    /**
     * Метод сортировки животных {@link Pet}
     * Поля для сортировки –  хозяин (имя человека) {@link part1.lesson02.task03.Person}, кличка животного, вес.
     * @param o1 - первый объект для сравнения
     * @param o2 - второй объект для сравнения
     * @return результат сравнения объектов
     */
    @Override
    public int compare(Pet o1, Pet o2) {
        if ((o1.getOwner().getName().equals(o2.getOwner().getName()))) {
            if (o1.getName().equals(o2.getName()))
                return Double.compare(o1.getWeight(),o2.getWeight());
             return o1.getName().compareTo(o2.getName());
        }
        return o1.getOwner().getName().compareTo(o2.getOwner().getName());
    }
}
