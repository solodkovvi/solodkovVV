package part1.lesson02.task03;


/**
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN)
 */
public class Sex {

    private String sex = "MAN";

    public void setSex(String sex) {
        if (sex.equals("WOMAN") || sex.equals("MAN")) {
            this.sex = sex;
        }
    }

    /**
     * Случайный выбор пола.
     * @param sex - проверка числового параметра на чётность и проставление пола случайно
     */
    public void setSex(int sex) {
        this.sex = (sex%2==0)?"MAN":"WOMAN";
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return this.getSex();
    }
}
