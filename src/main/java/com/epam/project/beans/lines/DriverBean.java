package com.epam.project.beans.lines;

public class DriverBean implements Line {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;

    public DriverBean() {}

    public DriverBean(int id) {
        this.id = id;
    }

    public DriverBean(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public DriverBean(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getColumn(int i) {
        String value;
        switch(i) {
            case 0:
                value = String.valueOf(getId());
                break;
            case 1:
                value = getName();
                break;
            case 2:
                value = getSurname();
                break;
            default:
                value = "none";
        }
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
