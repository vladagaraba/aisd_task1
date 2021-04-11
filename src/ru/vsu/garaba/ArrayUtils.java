package ru.vsu.garaba;

public class ArrayUtils {
    public static <T> boolean contains(T[] array, T value){
        for (int i = 0; i < array.length; i++) { // есть ли у нас в массиве какой-то опр элемент
            if (array[i].equals(value))
                return true;
        }
        return false;
    }
}
