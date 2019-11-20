package part1.lesson08.task1;

import java.io.*;
import java.lang.reflect.Field;

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
public class CustomSerializer {
    /**
     * Переменная-счетчик для работы с массивом при сериализации.
     */
    private static int serializeCounter = 0;

    /**
     * Метод сохранения объектов в файл
     * @param object объект для сохранения
     * @param file путь к файлу для сохранения
     */
    public static void serialize( Object object, String file) {
        String tmp = writeClass(object,0);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] buffer = tmp.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод описания объекта в xml. формат
     * <className>
     *     <fieldName>fieldValue</fieldName>
     *     ...
     * </className>
     * При наличии вложенного объекта описывает его так же внутри объекта
     * @param object объект для сериализации
     * @param level отступ перед "<" в табах
     * @return строка с описанным классом
     */
    private static String writeClass(Object object, int level)  {
        StringBuilder sb = new StringBuilder();
        StringBuilder tab = new StringBuilder();
        for (int i=0;i<level;i++){ //вычисляем необходимое
            tab.append("\t");
        }
        sb.append(tab+"<"+object.getClass().getName()+">\n");
        sb.append(writeFields(object,++level));
        sb.append(tab+"</"+object.getClass().getName()+">\n");
        return sb.toString();
    }

    /**
     * Метод записи полей объекта в xml.
     * формат <fieldName>fieldValue</fieldName>
     * @param obj объект для записи
     * @param level количество отступов в табах
     * @return строка со всеми полями и их значениями
     */
    private static String writeFields(Object obj, int level) {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        StringBuilder tab = new StringBuilder();
        for (int i=0;i<level;i++){
            tab.append("\t");
        }
        for (Field declaredField : fields) {
            try {
                declaredField.setAccessible(true);
                if (isPrimitive(declaredField.get(obj).getClass().getSimpleName())) {
                    sb.append(tab + "<" + declaredField.getName() + ">");
                    sb.append(declaredField.get(obj));
                    sb.append("</" + declaredField.getName() + ">\n");
                } else {
                    sb.append(writeClass(declaredField.get(obj), level));
                }
            }
            catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * Проверка на примитив и строку. getClass().getName() возвращает класс обёртку для примитива.
     * @param className имя класса в строку
     * @return true - класс примитив или строка, false - ссылочный класс
     */
    private static boolean isPrimitive(String className){
        return (className.equalsIgnoreCase("String") ||
                className.equalsIgnoreCase("Boolean") ||
                className.equalsIgnoreCase("Integer") ||
                className.equalsIgnoreCase("Double") ||
                className.equalsIgnoreCase("Float") ||
                className.equalsIgnoreCase("Char") ||
                className.equalsIgnoreCase("Byte") ||
                className.equalsIgnoreCase("Short") ||
                className.equalsIgnoreCase("Long"));
    }

    /**
     * Метод создания объекта по xml описанию из файла
     * @param file файл для создания объекта
     * @return объект собранный по xml
     */
    public static Object deSerialize(String file) {
        serializeCounter = 0;
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                sb.append((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //Разбираем файл и создаём массив строк по каждой записи.
        //В дальнейшем будем работать с массивом для упрощения обращений
        String[] strArray = sb.toString().replace("\t","").split("\n");

        return readClass(strArray,0);
    }

    /**
     * Метод создания объекта через рефлексию.
     * Для разбора xml разбираем возможные значения в строках:
     * Возможны 3 структуры
     * 1. <className> - начало класса
     * 2. <fieldName>value</fieldName> - параметр класса
     * 3. </className> - конец класса
     * @param objString массив строк для создания объекта
     * @param head - номер строки с концом класса </className>
     */
    private static Object readClass(String[] objString, int head)  {
        int tail = head;
        Object myObject = null;
        //Находим конец класса. Ищем </className> до перового появления
        for (int i = head;i<objString.length;i++){
            if (objString[i].contains("</" + objString[head].replaceAll("<", ""))){
                tail = i;
                break;
            }
        }
        if (tail!=head) {//это класс. Необходимо добавить параметры
            try {
                //Через рефлексию инициилизируем объект класса с пустыми параметрами
                Class clazz = Class.forName(objString[head].replace("<","").replace(">",""));
                myObject = clazz.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            Field[] fields = myObject.getClass().getDeclaredFields();
            for (Field field:fields) {
                //Проходим по всем параметрам сдвигая счётчик полей
                serializeCounter++;
                try {
                    field.setAccessible(true);
                    //Проверка примитива или вложенного класса и соответствующее транспонирование объекта в параметр класса
                    if (field.getType().isPrimitive() || isPrimitive(field.getType().getSimpleName())) {
                        switch (field.getType().getSimpleName().toLowerCase()) {
                            case "boolean": {
                                field.setBoolean(myObject, Boolean.parseBoolean(objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "integer":
                            case "int": {
                                field.setInt(myObject, Integer.parseInt(objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "double": {
                                field.setDouble(myObject, Double.parseDouble(objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "float": {
                                field.setFloat(myObject, Float.parseFloat(objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "string": {
                                field.set(myObject, (objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "long": {
                                field.setLong(myObject, Long.parseLong(objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "short": {
                                field.setShort(myObject, Short.parseShort(objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "byte": {
                                field.setByte(myObject, Byte.parseByte(objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))));
                                break;
                            }
                            case "char": {
                                field.setChar(myObject, (objString[serializeCounter].substring(objString[serializeCounter].indexOf(">") + 1, objString[serializeCounter].indexOf("</"))).toCharArray()[0]);
                                break;
                            }
                            default: {
                                System.out.println("Неопознанный примитив");
                            }
                        }
                    } else {
                        Object tmp = readClass(objString, serializeCounter);
                        field.set(myObject, tmp);
                    }
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        serializeCounter=tail;//конец класса, нужно пропустить завершающий тег
        return myObject;
    }
}

