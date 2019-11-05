package part1.lesson02.task03;


/**
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN)
 */
public class Sex {
    /**
     * поле sex отвечающее за пол объекта {@link Person#getSex()}
     */
    private String sex = "MAN";

    /**
     * Функция внесения значения в параметр {@link Sex#sex}
     * @param sex строковое значение вносимого параметра
     */
    public void setSex(String sex) {
        if (sex.equals("WOMAN") || sex.equals("MAN")) {
            this.sex = sex;
        }
    }

    /**
     * Функция рандомного выбор пола {@link Sex#sex}
     * проверка числового параметра на чётность и проставление пола случайно
     * @param sex - числовой параметр для проверки
     */
    public void setSex(int sex) {
        this.sex = (sex%2==0)?"MAN":"WOMAN";
    }

    /**
     * Функция получения строкового значения параметра {@link Sex#sex}
     * @return строковое значение параметра sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * метод получения строкового значения параметра {@link Sex#sex}
     * @return строковое значение параметра sex
     */
    @Override
    public String toString() {
        return this.getSex();
    }
}
