package part1.lesson06.task1;
/**
 * Задание 1. Написать программу, читающую текстовый файл.
 * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
 * Найденные слова не должны повторяться, регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 * @author SolodkovVV
 */
public class BodyVocabulary {

    public static void main(String[] args) {
        System.out.println("Нашли и прочитали текстовый файл:");
        String text = VocabularyWorker.readFileToString("files\\book.txt");
        System.out.println(text);
        if (VocabularyWorker.createVocabulary("files\\vocabulary.txt", text)){
            System.out.println("Словарь успешно записан");
        }
    }
}
