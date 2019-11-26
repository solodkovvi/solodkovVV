package part1.lesson11.task1;


import part1.lesson02.task03.Person;
import part1.lesson05.task1.Pet;

import java.util.Scanner;
import java.util.UUID;

/**
 * Задание: Перевести одну из предыдущих работ на использование стримов и
 * лямбда-выражений там, где это уместно (возможно, жертвуя производительностью)
 */
public class Main {

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
        System.out.println("Печать списка животных");
        catalog.print();
        System.out.println("-------------------");
        System.out.println("Ищем домашнее животное с кличкой Барбос: " + catalog.findPet("Барбос"));
        System.out.println("Сортируем и выводим список:");
        catalog.sortPrint();
        System.out.println("-----------------");
        Scanner in = new Scanner(System.in);
        System.out.print("Введите uuid для поиска и изменения животного: ");
        String uuidToChange = in.next();
        in.close();
        catalog.changePet(UUID.fromString(uuidToChange),"Шерхан", ownerThree, 300.0);
        catalog.sortPrint();
    }
}
