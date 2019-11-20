package part1.lesson06.task2;

import part1.lesson06.task1.VocabularyWorker;

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
public class BodyWordGenerator {
    public static void main(String[] args) {
        //Создаём объект с автоматическим словарём
        WordGenerator wg = new WordGenerator();
        //Заполняем словарь ранее сгенерированными словами из файла-справочника
        wg.vocabularyCustom(VocabularyWorker.readVocabulary("files\\vocabulary.txt"));
        //Генерируем файлы по заданному пути в количестве n с 6 абзацами и вероятностью включения слов из словаря
        wg.getFiles("files\\output\\",10,6,2000);
    }
}