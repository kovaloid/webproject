package com.epam.project.utils;

import java.util.ArrayList;
import java.util.List;

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
