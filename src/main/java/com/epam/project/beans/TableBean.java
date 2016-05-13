package com.epam.project.beans;

import java.util.List;

public class TableBean<T> {
    private List<String> headers;
    private List<T> lines;
    private int countColumns;
    private int countLines;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<T> getLines() {
        return lines;
    }

    public void setLines(List<T> lines) {
        this.lines = lines;
    }

    public int getCountColumns() {
        return countColumns;
    }

    public void setCountColumns(int countColumns) {
        this.countColumns = countColumns;
    }

    public int getCountLines() {
        return countLines;
    }

    public void setCountLines(int countLines) {
        this.countLines = countLines;
    }
}
