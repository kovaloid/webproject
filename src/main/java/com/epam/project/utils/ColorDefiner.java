package com.epam.project.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This util class help to define and get all colors of cars.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class ColorDefiner {
    private static final List<String> colors = new ArrayList<>();

    static {
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
}
