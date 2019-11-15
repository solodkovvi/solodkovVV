package part1.lesson06.task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Класс с набором методов для работы с файлами
 * @author SolodkovVV
 */
public class VocabularyWorker {
    /**
     * Метод для создания сортированного по алфавиту словаря по тексту. Без дубликатов.
     * @param text текст для создания словаря
     * @return сортированный словарь-коллекцию
     */
    private static List<String> textToCollection(String text){
        Set<String> vocabulary = new HashSet<>();
        text = text.replaceAll("[\\n]"," ").replaceAll("[^A-Za-z_ ]","");
        String[] tmp = text.split(" ");
        for (String elem:tmp) {
            vocabulary.add(elem.toLowerCase());
        }
        List<String> result= new ArrayList<>();
        result.addAll(vocabulary);
        result.sort(String::compareTo);
        return result;
    }

    /**
     * Считывает файл в строку
     * @param fileName строка доступа к файлу
     * @return текст файла
     */
    public static String readFileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                sb.append((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    /**
     * Создание файла-словаря по тексту
     * @param fileName название файла и путь к нему
     * @param text текст для создания словаря.
     * @return результат создания. true - файл создан, false - файл не создан
     */
    public static boolean createVocabulary(String fileName, String text){
        List<String> vc = textToCollection(text);
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            for (Iterator iterator = vc.iterator();iterator.hasNext();) {
                byte[] buffer = (iterator.next() +"\n").getBytes();
                fileOutputStream.write(buffer, 0, buffer.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Получить массив слов по файлу-словарю
     * @param fileName имя и путь к файлу
     * @return словарь в виде массива
     */
    public static String[] readVocabulary(String fileName){
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                sb.append((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString().split("\n");
    }
}
