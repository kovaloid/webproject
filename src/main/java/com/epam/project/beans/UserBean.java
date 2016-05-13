package com.epam.project.beans;

public class UserBean {
    private Integer id;
    private String login;
    private String password;
    private String role;

    public UserBean() {
    }

    public UserBean(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserBean(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
