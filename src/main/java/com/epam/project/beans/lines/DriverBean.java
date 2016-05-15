package com.epam.project.beans.lines;

public class DriverBean implements Line {
    private Integer id;
    private String name;
    private String surname;
    private String gender;
    private Integer phone;

    public DriverBean() {
    }

    public DriverBean(Integer id) {
        this.id = id;
    }

    public DriverBean(String name, String surname, String gender, Integer phone) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phone = phone;
    }

    public DriverBean(int id, String name, String surname, String gender, Integer phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phone = phone;
    }

    @Override
    public String getColumn(int i) {
        String value;
        switch (i) {
            case 0:
                value = String.valueOf(getId());
                break;
            case 1:
                value = getName();
                break;
            case 2:
                value = getSurname();
                break;
            case 3:
                value = getGender();
                break;
            case 4:
                value = String.valueOf(getPhone());
                break;
            default:
                value = NULL_VALUE;
        }
        if (value == null) return NULL_VALUE;
        return value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
