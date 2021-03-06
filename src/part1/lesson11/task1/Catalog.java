package part1.lesson11.task1;

import part1.lesson02.task03.Person;
import part1.lesson05.task1.ComparatorPet;
import part1.lesson05.task1.Pet;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для описания коллекции каталога животных
 */
public class Catalog {
    /**
     * Каталог животных
     */
    private Map<UUID, Pet> catalog;

    /**
     * Конструктор для создания каталога
     */
    public Catalog(){
        catalog = new HashMap<>();
    }

    /**
     * Метод добавления животного в каталог {@link Pet}
     * @param pet - животное для добавления
     */
    public void addPet(Pet pet){
        if (catalog.containsKey(pet.getUuid())) {//проверяю дубликат по ключу, можно переключится на проверку дубля по value containsValue(pet)
            throw new IllegalArgumentException("Добавление дубликата:" + pet.toString());
        }
        catalog.put(pet.getUuid(),pet);
    }

    /**
     * Метод печати отсортированного списка животных
     */
    public void sortPrint(){
        List tmp = new ArrayList<>(catalog.values());
        tmp.stream().sorted(new Comparator<Pet>() {
                                @Override
                                public int compare(Pet o1, Pet o2) {
                                    if ((o1.getOwner().getName().equals(o2.getOwner().getName()))) {
                                        if (o1.getName().equals(o2.getName()))
                                            return Double.compare(o1.getWeight(),o2.getWeight());
                                        return o1.getName().compareTo(o2.getName());
                                    }
                                    return o1.getOwner().getName().compareTo(o2.getOwner().getName());
                                }
                            }).forEach(o -> System.out.println(o.toString()));
//        tmp.stream().sorted(new ComparatorPet()).forEach(o -> System.out.println(o.toString()));
//        tmp.sort(new ComparatorPet());
//        for (Iterator iterator = tmp.iterator();iterator.hasNext();){
//            System.out.println(iterator.next());
//        }
    }

    /**
     * Метод печати всех объектов коллекции
     */
    public void print(){
        catalog.values().forEach(pet -> System.out.println(pet.toString()));
    }

    /**
     * Метод поиска животного
     * @param name - кличка для поиска
     * @return найденный объект Pet {@link Pet}
     */
    public Pet findPet(String name){
        try {
            return (new ArrayList<>(catalog.values())).stream()
                .filter(pet -> pet.getName().equals(name))
                .collect(Collectors.toList()).get(0);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Нет такого животного");
            return  null;
        }
//        for (Iterator iterator = catalog.values().iterator();iterator.hasNext();){
//            Pet tmp = (Pet)iterator.next();
//            if(tmp.getName().equals(name)){
//                return tmp;
//            }
//        }
//        return null;
    }

    /**
     * Изменить параметры животного, найденного по уникальному идентиифкатору
     * @param uuid - уник. идент для поиска
     * @param newName - новая кличка. Если null - не меняется.
     * @param newOwner - новый владелец. Если null - не меняется.
     * @param newWeight - новый вес животного. Если 0 - не меняется.
     */
    public void changePet(UUID uuid, String newName, Person newOwner, double newWeight){
        Pet tmp = catalog.get(uuid);
        tmp.changePet(newName, newOwner, newWeight);
    }
}
