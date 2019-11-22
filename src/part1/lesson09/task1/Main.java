package part1.lesson09.task1;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Дан интерфейс
 * public interface Worker {
 *     void doWork();
 * }
 * Необходимо написать программу, выполняющую следующее:
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 * @author SolodkovVV
 */
public class Main {
    public static void main(String[] args)  {
        //Создаём старую версию и выполняем
        System.out.println("Выполнение метода doWork ранее скомпилированного класса SomeClass");
        SomeClass sc = new SomeClass();
        sc.doWork();

        System.out.println("Введите новый код метода doWork для компиляции и выполнения. Для завершения ввода введите пустую строку.");
        //загружаем новый самописный класс
        createNewClassAndCompile();

        System.out.println("Загрузка нового класса и выполнения метода doWork");
        try {
        //получаем новый скомпилированный класс
        SomeClassLoader loader = new SomeClassLoader();
        Class<?> cls = loader.loadClass("SomeClass");
            cls.getMethod("doWork").invoke(cls.newInstance());
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private static void createNewClassAndCompile()  {
        File sourceFile = new File("./src/part1/lesson09/task1/SomeClass.java");
        sourceFile.getParentFile().mkdirs();
        try {
            Files.writeString(sourceFile.toPath(), writeDoMethod());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null,null,null,sourceFile.getPath());
    }

    private static String writeDoMethod() {
        Scanner in = new Scanner(System.in);
        String tmp = in.nextLine();
        StringBuilder sb = new StringBuilder();
        sb.append("package part1.lesson09.task1; public class SomeClass implements Worker { public SomeClass(){}  @Override public void doWork() {");
        while (!tmp.isEmpty()){
            sb.append(tmp);
            tmp = in.nextLine();
        }
        sb.append("}}");
        return sb.toString();
    }
}
