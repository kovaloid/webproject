package com.epam.project.beans.lines;

public class CarBean implements Line {
    private Integer id;
    private String number;
    private String color;
    private String mark;
    private Integer driverId;
    private String driverName;
    private String driverSurname;
    private Boolean ready;

    public CarBean() {
    }

    public CarBean(Integer id) {
        this.id = id;
    }

    public CarBean(String number, String mark, String color, Integer driverId) {
        this.number = number;
        this.mark = mark;
        this.color = color;
        this.driverId = driverId;
    }

    public CarBean(Integer id, String number, String mark, String color, Integer driverId) {
        this.id = id;
        this.number = number;
        this.mark = mark;
        this.color = color;
        this.driverId = driverId;
    }

    @Override
    public String getColumn(int i) {
        String value;
        switch (i) {
            case 0:
                value = String.valueOf(getId());
                break;
            case 1:
                value = getNumber();
                break;
            case 2:
                value = getColor();
                break;
            case 3:
                value = getMark();
                break;
            case 4:
                value = getDriverName();
                break;
            case 5:
                value = getDriverSurname();
                break;
            default:
                value = "---";
        }
        return value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }
}
