package com.epam.project.beans.lines;

/**
 * Car Bean.
 * <p>It's is a data model of one car.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class CarBean implements Line {
    private Integer id;
    private String number;
    private String color;
    private String brand;
    private Integer driverId;
    private String driverName;
    private String driverSurname;
    private String ready;

    public CarBean() {
    }

    public CarBean(Integer id) {
        this.id = id;
    }

    public CarBean(String number, String brand, String color, Integer driverId, String ready) {
        this.number = number;
        this.brand = brand;
        this.color = color;
        this.driverId = driverId;
        this.ready = ready;
    }

    public CarBean(Integer id, String number, String brand, String color, Integer driverId, String ready) {
        this.id = id;
        this.number = number;
        this.brand = brand;
        this.color = color;
        this.driverId = driverId;
        this.ready = ready;
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
                value = getBrand();
                break;
            case 4:
                value = getDriverName();
                break;
            case 5:
                value = getDriverSurname();
                break;
            case 6:
                value = getReady();
                break;
            default:
                value = NULL_VALUE;
        }
        if (value == null) return NULL_VALUE;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getReady() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }
}
