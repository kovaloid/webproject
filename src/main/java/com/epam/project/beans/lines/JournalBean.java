package com.epam.project.beans.lines;

import java.sql.Date;

public class JournalBean implements Line {
    private Integer id = null;
    private Integer carId;
    private String number;
    private Date timeIn;
    private Date timeOut;
    private Integer routeId;
    private String routeName;
    private String driverSurname;

    public JournalBean() {
    }

    public JournalBean(int id) {
        this.id = id;
    }

    public JournalBean(Integer id, Date date_in) {
        this.id = id;
        this.timeIn = date_in;
    }

    public JournalBean(Date date_out, String number, int routeId) {
        this.timeOut = date_out;
        this.number = number;
        this.routeId = routeId;
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
                if (getTimeOut() == null)
                    value = "---";
                else
                    value = getTimeOut().toString();
                break;
            case 3:
                if (getTimeIn() == null)
                    value = "---";
                else
                    value = getTimeIn().toString();
                break;
            case 4:
                value = getRouteName();
                break;
            case 5:
                value = getDriverSurname();
                break;
            default:
                value = "none";
        }
        return value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }
}
