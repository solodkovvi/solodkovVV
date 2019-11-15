package part1.lesson06.task2;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * Задание 2. Создать генератор текстовых файлов, работающий по следующим правилам:
 * Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 * Слово состоит из 1<=n2<=15 латинских букв
 * Слова разделены одним пробелом
 * Предложение начинается с заглавной буквы
 * Предложение заканчивается (.|!|?)+" "
 * Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 * Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 *  который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 * @author SolodkovVV
 */
public class WordGenerator {
    /**
     * Предложение состоит из 1<=n1<=15 слов
     */
    final int N1 = 15;
    /**
     * Слово состоит из 1<=n2<=15 латинских букв
     */
    final int N2 = 15;
    /**
     * Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений
     */
    final int N3 = 20;
    /**
     * Есть массив слов 1<=n4<=1000
     */
    final int N4 = 1000;
    /**
     *Справочник, который генерируется со случайными словами при создании объекта
     */
    String[] vocabulary = new String[N4];

    /**
     * Конструктор с генериацией словаря
     */
    public WordGenerator(){
        for (int i = 0;i<vocabulary.length;i++){
            vocabulary[i] = createWord(new Random().nextInt(N1)+1);//Наполняем словарь случайными словами случайной длинны от 1 до 15
        }
    }

    /**
     * Заполнение словаря собственными значениями
     * @param array - новый массив для словаря
     */
    public void vocabularyCustom(String[] array){
        vocabulary = new String[array.length];
        vocabulary = array;
    }

    /**
     * Метод генерации слова по произвольной длинне
     * @param power - произвольная длинна слова
     * @return - сгенерированное слово
     */
    private String createWord(int power) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<power;i++){
            sb.append((char)(new Random().nextInt(26)+97));//ASCII a-z элементы с 97 по 122
        }
        return sb.toString();
    }

    /**
     * Метод генерации строки по произвольной длинне с выбором слов из словаря по заданной вероятности 1\p.
     * Первая буква заглавная, в конце знак конца строки.
     * @param power - количество слов в строке
     * @param p - вероятность попадания слова из массива 1\p
     * @return - сгенерированная строка.
     */
    private String createLine(int power, int p){
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        String result="";
        if (power<=0) {
            throw new IllegalArgumentException("Количество слов в строке не может быть отрицательным, либо равным 0");
        }
        while (counter!=power){
            for (String ell:vocabulary){
                if (Math.random() < 1.0 / p) {
                    counter++;
                    sb.append(" " +ell);
                }
                if (counter==power) {
                    return sb.substring(1,2).toUpperCase()+sb.substring(2)+generateLineEnd();
                }
            }
        }
        return sb.substring(1,2).toUpperCase()+sb.substring(2)+generateLineEnd();
    }

    /**
     * Метод генерации строки по произвольной длинне с выбором случайного слова из словаря.
     * Первая буква заглавная, в конце знак конца строки.
     * @param power количество слов в строке
     * @return сгенерированная строка
     */
    private String createLine(int power){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<power;i++){
            sb.append(" " +vocabulary[new Random().nextInt(vocabulary.length)]);
        }
        return sb.substring(1,2).toUpperCase()+sb.substring(2)+generateLineEnd();
    }

    /**
     * Метод генерации окончания строки из символов
     * @return символ окончания строки
     */
    private String generateLineEnd(){
        int r = new Random().nextInt(3);
        switch (r) {
            case (0):
                return "! ";
            case (1):
                return "? ";
            case(2):
                return ". ";
            default:
                return "";
        }
    }

    /**
     * Метод генерации абзаца с количеством строк по заданному параметру и вероятностью попадания слова из словаря 1/p
     * @param power количество строк
     * @param p вероятность попадания слова из словаря
     * @return сгенерированный абзац
     */
    private String createParagraph(int power, int p){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<power;i++){
            sb.append(createLine(new Random().nextInt(N2)+1, p));
        }
        return sb.substring(0,sb.length()) + "\n";
    }

    /**
     * Метод создания файла с заданным количеством абзацев.
     * @param power количество абзацев
     * @param fileName путь к файлу, в который нужно сохранить текст
     * @param p вероятность попадания слова из словаря в текст 1\p
     */
    public void createFile(int power, String fileName, int p){
        StringBuilder sb= new StringBuilder();
        for (int i=0;i<power;i++){
            sb.append(createParagraph(N3, p));
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                byte[] buffer = sb.toString().getBytes();
                fileOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
     * @param path - путь для файла, где необходимо его сохранить
     * @param n - количество файлов для создания
     * @param size - размер файлов в абзацах
     * @param p вероятность попадания слова из словаря в текст 1\p
     */
    public void getFiles(String path, int n, int size, int p){ //, String[] words, int probability
        for (int i=0;i<n;i++){
            createFile(size,path+ UUID.randomUUID().toString()+".txt", p);
        }
    }
}
