package com.epam.project.beans;

import java.util.List;

public class TableBean<T> implements Table<T> {
    private List<String> headers;
    private List<T> lines;
    private int countColumns;
    private int countLines;

    @Override
    public List<String> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    @Override
    public List<T> getLines() {
        return lines;
    }

    @Override
    public void setLines(List<T> lines) {
        this.lines = lines;
    }

    @Override
    public int getCountColumns() {
        return countColumns;
    }

    @Override
    public void setCountColumns(int countColumns) {
        this.countColumns = countColumns;
    }

    @Override
    public int getCountLines() {
        return countLines;
    }

    @Override
    public void setCountLines(int countLines) {
        this.countLines = countLines;
    }
}
