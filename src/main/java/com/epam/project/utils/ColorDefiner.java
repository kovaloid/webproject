package com.epam.project.utils;

import java.util.ArrayList;
import java.util.List;

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
