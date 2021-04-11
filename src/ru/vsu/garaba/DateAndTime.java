package ru.vsu.garaba;

import java.util.Scanner;

public class DateAndTime {
    private int days; // с начала летоисчесления
    private int secundes; // начинается с секунд\

    private static boolean isYearLeap(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0 || year == 1;
    }

    private static int howMuchDaysInMonth(int month, int year) {
        Integer[] month30 = {4, 6, 9, 11};
        Integer[] month31 = {1, 3, 5, 7, 8, 10, 12};

        if (ArrayUtils.contains(month31, month))
            return 31;
        if (ArrayUtils.contains(month30, month))
            return 30;
        if (month == 2 && isYearLeap(year))
            return 29;
        if (month == 2 && !isYearLeap(year))
            return 28;

        return -1;
    }

    public DateAndTime(int days, int secundes) {
        this.days = days;
        this.secundes = secundes;
        checkTime();
    }

    public void addDays(int days) {
        this.days += days;
    }

    public void addMonths(int months) {
        this.days += 30 * months;
    }

    public void addYears(int years) {
        this.days += years * 365;
    }

    public int getCurrentDateInDays() {
        return this.days;
    }

    private static int getDaysInDate(String date) throws Exception {
        try {
            //toDO запихнуть ВООБЩЕ ВСЁ try catch (ты сказала, что знаешь как это делать :b)

            // Строка формата XX.YY.ZZZZ
            int daysCounter = 0;

            Scanner scn = new Scanner(date).useDelimiter("[^0-9]");

            int days = scn.nextInt();
            int month = scn.nextInt();
            int years = scn.nextInt();

            if (days < 1 || days > 31 || month < 1 || month > 12 || days > howMuchDaysInMonth(month, years) || years < 1)
                throw new Exception("Wrong date exeption");

            daysCounter += days - 1;

            while (years != 1 || month != 1) {
                month--;
                if (month == 0) {
                    month = 12;
                    years--;
                }
                daysCounter += howMuchDaysInMonth(month, years); // добавляем кол-во дней в текущиц месяц
            }
            return daysCounter;
        } catch (Exception e) {
            System.out.println("Sorry, your worry");
        }
        return -1;
    }

    public static int howMuchDaysBetweenTwoDates(String date1, String date2) throws Exception {
        int daysDate1 = getDaysInDate(date1);
        int daysDate2 = getDaysInDate(date2);

        return Math.abs(daysDate1 - daysDate2); // без разница какая дата первая
    }

    public void setDate(String date) throws Exception {
        this.days = getDaysInDate(date);
    }

    public void setTime(String time) throws Exception {
        //Строка формата XX:YY:ZZ
        int secundes = 0;
        Scanner scn = new Scanner(time).useDelimiter("[^0-9]"); // только числа от 0-9 можно вводить

        int temp = scn.nextInt();
        if (temp > 23) throw new Exception("Wrong hours enter exeption");
        secundes += temp * 3600;

        temp = scn.nextInt();
        if (temp > 59) throw new Exception("Wrong minutes enter exeption");
        secundes += temp * 60;

        temp = scn.nextInt();
        if (temp > 59) throw new Exception("Wrong secundes enter exeption");
        secundes += temp;

        this.secundes = secundes;
    }

    public void addSecundes(int secundes) {
        this.secundes += secundes;
        checkTime();
    }

    public void addMinutes(int minutes) {
        this.secundes += minutes * 60;
        checkTime();
    }

    public void addHours(int hours) {
        this.secundes += hours * 3600;
        checkTime();
    }

    public String getTime() { // получаем время
        StringBuilder stb = new StringBuilder();
        int temp; // временная переменная

        temp = this.secundes / 3600;
        if (temp < 10) stb.append('0'); // что это???
        stb.append(temp);
        stb.append(':');

        temp = this.secundes / 60 % 60;
        if (temp < 10) stb.append('0');
        stb.append(temp);
        stb.append(':');

        temp = this.secundes % 60 % 60;
        if (temp < 10) stb.append('0');
        stb.append(temp);

        return stb.toString();
    }

    private void checkTime() {
        if (this.secundes >= 86400) { // если текущая секунда, которую мы ввели больше
            // чем 86400(кол-во секунд в сутка), то у нас будем прибавляться
            // новый день + секунды к нему

            this.days += this.secundes / 86400; // кол-во целых дней
            this.secundes = this.secundes % 86400;
        }
    }

}


