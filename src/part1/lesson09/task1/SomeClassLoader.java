package part1.lesson09.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс для загрузки скомпилированного файла.class
 */
public class SomeClassLoader extends ClassLoader {
    /**
     * Метод загрузки класса по названию
     * @param name название класса для загрузки
     * @return класс
     * @throws ClassNotFoundException возвращает ошибку в случае не нахождения класса
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("SomeClass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * Метод поиска класса, соответствующего входному name
     * @param name название класс для поиска
     * @return класс
     * @throws ClassNotFoundException возвращает ошибку в случае не нахождения класса
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("SomeClass".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("./src/part1/lesson09/task1/SomeClass.class"));
                return defineClass("part1.lesson09.task1.SomeClass", bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
