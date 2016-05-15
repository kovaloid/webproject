package com.epam.project.utils;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.Line;

import java.util.ArrayList;
import java.util.List;

public class TableSeparator {

    //@SuppressWarnings("unchecked")
    public static Table[] separate(int sizeOfOneCut, Table oldTable) {
        int countOfNewTables = (int) ((float) oldTable.getCountLines() / sizeOfOneCut);

        System.out.println(countOfNewTables);

        Table<Line>[] newTables = null;
        List<Line>[] linesInNewTables = null;
        try {
            newTables = (TableBean<Line>[]) new TableBean[countOfNewTables];
            linesInNewTables = (ArrayList<Line>[]) new ArrayList[sizeOfOneCut];
            for (int i = 0; i < countOfNewTables; i++) {
                newTables[i] = new TableBean<>();
                linesInNewTables[i] = new ArrayList<>();
            }

            for (int i = 0; i < countOfNewTables; i++) {
                for (int j = 0; j < sizeOfOneCut; j++) {

                    linesInNewTables[i].add((Line) oldTable.getLines().get(i * sizeOfOneCut + j));

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        for (int i = 0; i < countOfNewTables; i++) {
            newTables[i].setCountColumns(oldTable.getCountColumns());
            newTables[i].setCountLines(linesInNewTables[i].size());
            newTables[i].setHeaders(oldTable.getHeaders());
            newTables[i].setLines(linesInNewTables[i]);
        }

        return newTables;
    }
}
