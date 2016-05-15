package com.epam.project.beans;

import java.util.List;

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
