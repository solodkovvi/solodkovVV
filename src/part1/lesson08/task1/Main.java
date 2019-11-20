package part1.lesson08.task1;

/**
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 * Требование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
 * @author SolodkovVV
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        ExampleClass et1 = new ExampleClass("Test1", 7, "First Test", 10.10, true);
        CustomSerializer.serialize(et1,"files\\object.txt");
        ExampleClass et2 = (ExampleClass)CustomSerializer.deSerialize("files\\object.txt");
        System.out.println("Объект первичный " + et1.toString());
        System.out.println("Объект копия: " + et2.toString());
        System.out.println("Объекты равны? " + et1.equals(et2));
    }
}
