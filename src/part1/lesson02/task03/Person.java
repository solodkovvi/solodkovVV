package part1.lesson02.task03;

import java.util.Random;

/**
 * Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN),
 * name (имя - строка).
 *
 *  Для формирования элементов реализован генератор случайных Person со своим набором правил для каждого поля.
 */
public class Person {

    enum enumManName {
        Liam,
        Noah,
        William,
        James,
        Oliver,
        Benjamin,
        Elijah,
        Lucas,
        Mason,
        Logan,
        Alexander,
        Ethan,
        Jacob,
        Michael,
        Daniel,
        Henry,
        Jackson,
        Sebastian,
        Aiden,
        Matthew,
        Samuel,
        David,
        Joseph,
        Carter,
        Owen,
        Wyatt,
        John,
        Jack,
        Luke,
        Jayden,
        Dylan,
        Grayson,
        Levi,
        Isaac,
        Gabriel,
        Julian,
        Mateo,
        Anthony,
        Jaxon,
        Lincoln,
        Joshua,
        Christopher,
        Andrew,
        Theodore,
        Caleb,
        Ryan,
        Asher,
        Nathan,
        Thomas,
        Leo,
        Isaiah,
        Charles,
        Josiah,
        Hudson,
        Christian,
        Hunter,
        Connor,
        Eli,
        Ezra,
        Aaron,
        Landon,
        Adrian,
        Jonathan,
        Nolan,
        Jeremiah,
        Easton,
        Elias,
        Colton,
        Cameron,
        Carson,
        Robert,
        Angel,
        Maverick,
        Nicholas,
        Dominic,
        Jaxson,
        Greyson,
        Adam,
        Ian,
        Austin,
        Santiago,
        Jordan,
        Cooper,
        Brayden,
        Roman,
        Evan,
        Ezekiel,
        Xavier,
        Jose,
        Jace,
        Jameson,
        Leonardo,
        Bryson,
        Axel,
        Everett,
        Parker,
        Kayden,
        Miles,
        Sawyer,
        Jason
    }
    enum enumWomanName {
        Emma,
        Olivia,
        Ava,
        Isabella,
        Sophia,
        Charlotte,
        Mia,
        Amelia,
        Harper,
        Evelyn,
        Abigail,
        Emily,
        Elizabeth,
        Mila,
        Ella,
        Avery,
        Sofia,
        Camila,
        Aria,
        Scarlett,
        Victoria,
        Madison,
        Luna,
        Grace,
        Chloe,
        Penelope,
        Layla,
        Riley,
        Zoey,
        Nora,
        Lily,
        Eleanor,
        Hannah,
        Lillian,
        Addison,
        Aubrey,
        Ellie,
        Stella,
        Natalie,
        Zoe,
        Leah,
        Hazel,
        Violet,
        Aurora,
        Savannah,
        Audrey,
        Brooklyn,
        Bella,
        Claire,
        Skylar,
        Lucy,
        Paisley,
        Everly,
        Anna,
        Caroline,
        Nova,
        Genesis,
        Emilia,
        Kennedy,
        Samantha,
        Maya,
        Willow,
        Kinsley,
        Naomi,
        Aaliyah,
        Elena,
        Sarah,
        Ariana,
        Allison,
        Gabriella,
        Alice,
        Madelyn,
        Cora,
        Ruby,
        Eva,
        Serenity,
        Autumn,
        Adeline,
        Hailey,
        Gianna,
        Valentina,
        Isla,
        Eliana,
        Quinn,
        Nevaeh,
        Ivy,
        Sadie,
        Piper,
        Lydia,
        Alexa,
        Josephine,
        Emery,
        Julia,
        Delilah,
        Arianna,
        Vivian,
        Kaylee,
        Sophie,
        Brielle,
        Madeline
    }
    private int age;
    private Sex sex;
    private String name;

    /**
     * метод получения параметра age
     * @return int age
     */
    public int getAge() {
        return age;
    }

    /**
     * метод задания параметра age
     * @param age - задаваемое значение age объекта Person
     */
    public void setAge(int age) {
        if (age>0 && age<=100) {
            this.age = age;
        } else this.age = 0;
    }

    /**
     * метод получения строкового значения поля Sex
     * @return строковое значение Sex
     */
    public String getSex() {
        return sex.getSex();
    }

    /**
     * метод задания параметра sex
     * @param sex объект класса sex
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * метод получения параметра name объекта Person
     * @return строковое значение name
     */
    public String getName() {
        return name;
    }

    /**
     * Случайный выбор из enumerables по параметру Пол
     * @param sex - значение для подбора случайного имени
     */
    public void setName(Sex sex) {
        if (this.getSex().equals("WOMAN")) {
            this.name = enumWomanName.values()[new Random().nextInt(enumWomanName.values().length)].toString();
        }
        else if (this.getSex().equals("MAN")) {
            this.name = enumManName.values()[new Random().nextInt(enumManName.values().length)].toString();
        }
    }

    /**
     * метод задания параметра name объекта Person
     * @param name строковое значение параметра name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * автоконструктор с рандомным подбором параметров Person age, name, sex
     */
    public Person() {
        Random rand = new Random();
        this.sex = new Sex();
        this.sex.setSex(rand.nextInt(2));
        this.setAge(rand.nextInt(100));
        this.setName(this.sex);
    }

    /**
     * конструктор с явным внесением параметров Person
     * @param name - имя Person
     * @param age - возраст Person
     * @param sex - пол Person
     */
    public Person(String name, int age, String sex) {
        this.sex = new Sex();
        this.sex.setSex(sex);
        this.setAge(age);
        this.setName(name);
    }

    /**
     * вывод параметров объекта Person в строку
     * @return значения параметров Person через запятую
     */
    @Override
    public String toString() {
        return this.getSex()+","+this.getAge()+","+this.getName();
    }
    
}
