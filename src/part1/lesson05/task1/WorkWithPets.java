package part1.lesson05.task1;

import part1.lesson02.task03.Person;

import java.util.Scanner;
import java.util.UUID;

/**
 * Разработать программу – картотеку домашних животных.
 * У каждого животного есть уникальный идентификационный номер, кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * Реализовать:
 * +метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * +поиск животного по его кличке (поиск должен быть эффективным)
 * +изменение данных животного по его идентификатору
 * +вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 * @author SolodkovVV
 */
public class WorkWithPets {
    public static void main(String[] args) {
        System.out.println("Создание базовых сущностей (Person, Pet, Catalog)");
        Person ownerOne = new Person();
        Person ownerTwo = new Person();
        Person ownerThree = new Person();
        Catalog catalog = new Catalog();
        for (int i =0;i<5;i++){
            catalog.addPet(new Pet(ownerOne));
            catalog.addPet(new Pet(ownerTwo));
            catalog.addPet(new Pet(ownerThree));
        }
        catalog.addPet(new Pet("Барбос", ownerOne,35.0));
        System.out.println("Ищем домашнее животное с кличкой Барбос: " + catalog.findPet("Барбос"));
        System.out.println("Сортируем и выводим список:");
        catalog.sortPrint();
        System.out.println("-----------------");
//        catalog.print();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите uuid для поиска и изменения животного: ");
        String uuidToChange = in.next();
        in.close();
        catalog.changePet(UUID.fromString(uuidToChange),"Шерхан", ownerThree, 300.0);
        catalog.sortPrint();
    }
}
