package com.epam.project.service;

import java.util.ArrayList;
import java.util.List;

public class Definer {
    private static List<String> monthes = new ArrayList<>();
    private static List<String> cars = new ArrayList<>();
    private static List<String> colors = new ArrayList<>();

    static {
        monthes.add("01");
        monthes.add("02");
        monthes.add("03");
        monthes.add("04");
        monthes.add("05");
        monthes.add("06");
        monthes.add("07");
        monthes.add("08");
        monthes.add("09");
        monthes.add("10");
        monthes.add("11");
        monthes.add("12");

        cars.add("Газель");
        cars.add("Мерседес");
        cars.add("Рено");
        cars.add("Пежо");
        cars.add("Лада");
        cars.add("Форд");
        cars.add("Волга");

        colors.add("синий");
        colors.add("красный");
        colors.add("желтый");
        colors.add("зеленый");
        colors.add("оранжевый");
        colors.add("коричневый");
        colors.add("черный");
        colors.add("белый");
    }

    public static List<String> getColorsList() {
        return colors;
    }

    public static List<String> getCarsList() {
        return cars;
    }

    public static List<String> getMonthesList() {
        return monthes;
    }

    public static String car(int i) {
        String car = null;
        switch (i) {
            case 1:
                car = "JAN";
                break;
            case 2:
                car = "FEB";
                break;
            case 3:
                car = "MAR";
                break;
            case 4:
                car = "APR";
                break;
            case 5:
                car = "MAY";
                break;
            case 6:
                car = "JUN";
                break;
            case 7:
                car = "JUL";
                break;
            default:
                car = "";
        }
        return car;
    }

    public static String color(int i) {
        return null;
    }
}
