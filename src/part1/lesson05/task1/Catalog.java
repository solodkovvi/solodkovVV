package part1.lesson05.task1;

import part1.lesson02.task03.Person;

import java.util.*;

/**
 * Класс для описания коллекции каталога животных
 */
public class Catalog {
    /**
     * Каталог животных
     */
    private HashMap<UUID, Pet> catalog;

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
        tmp.sort(new ComparatorPet());
        for (Iterator iterator = tmp.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
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
        for (Iterator iterator = catalog.values().iterator();iterator.hasNext();){
            Pet tmp = (Pet)iterator.next();
            if(tmp.getName().equals(name)){
                return tmp;
            }
        }
        return null;
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
