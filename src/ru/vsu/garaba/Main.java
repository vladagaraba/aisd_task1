package ru.vsu.garaba;

public class Main {

    public static void main(String[] args) throws Exception {
        DateAndTime dateAndTime = new DateAndTime(0,0);

        //toDO Сделать ЕЩЁ вот такой конструктор: DateAndTime dateAndTime = new DateAndTime("09.04.2021", "21:09:21");

        dateAndTime.setDate("08.01.0001");
        System.out.println(dateAndTime.getCurrentDateInDays());

        //System.out.println(DateAndTime.howMuchDaysBetweenTwoDates("17.10.2001", "09.04.2021"));
    }
}
