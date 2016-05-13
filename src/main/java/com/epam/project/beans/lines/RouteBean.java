package com.epam.project.beans.lines;

public class RouteBean implements Line {

    private Integer id = null;
    private String routeName;
    private Integer length;
    private Integer price;

    public RouteBean() {}

    public RouteBean(Integer id) {
        this.id = id;
    }

    public RouteBean(String routeName, Integer length, Integer price) {
        this.routeName = routeName;
        this.length = length;
        this.price = price;
    }

    public RouteBean(Integer id, String routeName, Integer length, Integer price) {
        this.id = id;
        this.routeName = routeName;
        this.length = length;
        this.price = price;
    }

    @Override
    public String getColumn(int i) {
        String value;
        switch(i) {
            case 0:
                value = String.valueOf(getId());
                break;
            case 1:
                if (getRouteName() == null)
                    value = "---";
                else
                    value = getRouteName();
                //value = getRouteName();
                break;
            default:
                value = "---";
        }
        return value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) { this.id = id;  }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String name) {
        this.routeName = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
