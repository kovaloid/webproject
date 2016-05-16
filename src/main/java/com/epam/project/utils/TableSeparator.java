package com.epam.project.utils;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.Line;
import com.epam.project.consts.Separator;

import java.util.ArrayList;
import java.util.List;

/**
 * This util class help to separate big tables to array of small tables.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class TableSeparator implements Separator {

    /**
     * This method convert String value of table number to int value.
     *
     * @param numberParam string number of table
     * @param tablesAmount amount of separated tables
     * @return 1.0
     */
    public static int getTableNumber(String numberParam, int tablesAmount) {
        int tableNumber;
        try {
            if (numberParam == null) numberParam = "1";
            tableNumber = Integer.valueOf(numberParam);
            if (tableNumber < 1 || tableNumber > tablesAmount) tableNumber = 1;
        } catch (NumberFormatException e) {
            tableNumber = 1;
        }
        return tableNumber;
    }

    /**
     * This method separate big table to array of small tables.
     *
     * @param sourceTable big table
     * @return array of small tables
     */
    public static Table<Line>[] separate(Table sourceTable) {
        return separate(sizeOfNewTable, sourceTable);
    }

    @SuppressWarnings("unchecked")
    private static Table<Line>[] separate(int sizeOfNewTable, Table sourceTable) {
        int sizeOfSourceTable = sourceTable.getCountLines();
        int countOfNewTables = (int) Math.ceil((double) sizeOfSourceTable / sizeOfNewTable);
        Table<Line>[] newTables = (TableBean<Line>[]) new TableBean[countOfNewTables];
        List<Line>[] linesInNewTables = (ArrayList<Line>[]) new ArrayList[countOfNewTables];
        for (int i = 0; i < countOfNewTables; i++) {
            newTables[i] = new TableBean<>();
            linesInNewTables[i] = new ArrayList<>();
        }
        Outer:
        for (int i = 0; i < countOfNewTables; i++) {
            for (int j = 0; j < sizeOfNewTable; j++) {
                int sourceTablePointer = i * sizeOfNewTable + j;
                if (sourceTablePointer >= sizeOfSourceTable) break Outer;
                linesInNewTables[i].add((Line) sourceTable.getLines().get(sourceTablePointer));
            }
        }
        for (int i = 0; i < countOfNewTables; i++) {
            newTables[i].setCountColumns(sourceTable.getCountColumns());
            newTables[i].setCountLines(linesInNewTables[i].size());
            newTables[i].setHeaders(sourceTable.getHeaders());
            newTables[i].setLines(linesInNewTables[i]);
        }
        return newTables;
    }
}
