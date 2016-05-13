package com.epam.project.beans.lines;

import java.sql.Date;

public class JournalBean implements Line {
    private Integer id = null;
    private Integer carId;
    private String number;
    private Date dateIn;
    private Date dateOut;
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
        this.dateIn = date_in;
    }

    public JournalBean(Date date_out, Integer carId, int routeId) {
        this.dateOut = date_out;
        this.carId = carId;
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
                if (getDateOut() == null)
                    value = "---";
                else
                    value = getDateOut().toString();
                break;
            case 3:
                if (getDateIn() == null)
                    value = "---";
                else
                    value = getDateIn().toString();
                break;
            case 4:
                value = getRouteName();
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

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
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
