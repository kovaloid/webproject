package com.epam.project.beans;

import java.util.List;

/**
 * Interface that represents one table.
 * <p>The interface provides methods by which you can get or set headers and
 * lines of table. Also it's possible to get amount of columns and lines.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public interface Table<T> {
    List<String> getHeaders();

    void setHeaders(List<String> headers);

    List<T> getLines();

    void setLines(List<T> lines);

    int getCountColumns();

    void setCountColumns(int countColumns);

    int getCountLines();

    void setCountLines(int countLines);
}
