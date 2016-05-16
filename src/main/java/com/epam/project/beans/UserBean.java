package com.epam.project.beans;

import com.epam.project.beans.lines.Line;

/**
 * User Bean.
 * <p>It's is a data model of one user.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class UserBean implements Line {
    private Integer id;
    private String login;
    private String password;
    private String role;

    public UserBean() {
    }

    public UserBean(String login) {
        this.login = login;
    }

    public UserBean(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String getColumn(int i) {
        String value;
        switch (i) {
            case 0:
                value = String.valueOf(getId());
                break;
            case 1:
                value = getLogin();
                break;
            case 2:
                value = getPassword();
                break;
            case 3:
                value = getRole();
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
