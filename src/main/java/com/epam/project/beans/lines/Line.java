package com.epam.project.beans.lines;

/**
 * Interface that represents one line in table.
 * <p>The interface provides a method by which you can consistently print columns
 * of a particular row to a JSP pages. There is also a constant, which is displayed
 * if the column value is null.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public interface Line {
    String NULL_VALUE = "---";

    /**
     * A method that returns the value of a column by its number.
     *
     * @param i column number in line
     * @return string stored in this column
     */
    String getColumn(int i);
}
