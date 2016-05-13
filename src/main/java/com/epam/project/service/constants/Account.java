package com.epam.project.service.constants;

public interface Account {

    String LOGIN = "username";
    String PASSWORD = "password";
    String REPEAT = "repeat";

    String USER = "user";
    String STATUS = "status";

    interface Role {
        String ADMIN = "admin";
        String CLIENT = "client";
    }

    interface Result {
        String SUCCESS = "success";
        String FAIL_LOGIN = "fail_username";
        String FAIL_PASSWORD = "fail_password";
        String FAIL_REPEAT = "fail_repeat";
    }

    interface Status {
        String IN = "in";
        String OUT = "out";
    }
}
