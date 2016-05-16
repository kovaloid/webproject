package com.epam.project.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This util class help to define and get all brands of cars.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class CarDefiner {
    private static List<String> cars = new ArrayList<>();

    static {
        cars.add("Газель");
        cars.add("Мерседес");
        cars.add("Рено");
        cars.add("Пежо");
        cars.add("Лада");
        cars.add("Форд");
        cars.add("Волга");
    }

    public static List<String> getCarsList() {
        return cars;
    }
}
