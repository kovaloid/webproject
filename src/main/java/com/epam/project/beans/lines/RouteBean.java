package com.epam.project.beans.lines;

public class RouteBean implements Line {

    private Integer id = null;
    private String name;

    public RouteBean() {}

    public RouteBean(Integer id) {
        this.id = id;
    }

    public RouteBean(String name) {
        this.name = name;
    }

    public RouteBean(Integer id, String name) {
        this.id = id;
        this.name = name;
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
            default:
                value = "none";
        }
        return value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) { this.id = id;  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
