package com.epam.project.service;

import com.epam.project.beans.UserBean;
import com.epam.project.database.dao.autobase.UsersDAO;
import com.epam.project.service.constants.Account;
import org.apache.log4j.Logger;

public class AccountManager {

    private final static Logger log = Logger.getRootLogger();

    private boolean isBadUsername(String login) {
        boolean condition_1 = (login.length() < 20) && (login.length() > 3);
        boolean condition_2 = UsersDAO.checkSameLogin(new UserBean(login));
        // Имя пользователя (с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ обязательно буква)
        //boolean condition_3 = username.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
        //return !(condition_1 && condition_2 && condition_3);
        return !(condition_1 && condition_2);
    }

    private boolean isBadPassword(String password) {
        boolean condition_1 = (password.length() < 20) && (password.length() > 3);
        //Пароль (Строчные и прописные латинские буквы, цифры):
        //boolean condition_2 = password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
        //return !(condition_1 && condition_2);
        return !(condition_1);
    }

    private boolean isBadRepeat(String password, String repeat) {
        return !password.equals(repeat);
    }

    public String signup(String login, String password, String repeat) {
        boolean bad_login = isBadUsername(login);
        boolean bad_password = isBadPassword(password);
        boolean bad_repeat = isBadRepeat(password, repeat);
        if (bad_login || bad_password || bad_repeat) {
            if (bad_login) return Account.Result.FAIL_LOGIN;
            if (bad_password) return Account.Result.FAIL_PASSWORD;
            if (bad_repeat) return Account.Result.FAIL_REPEAT;
        }
        return Account.Result.SUCCESS;
    }

    public String authenticate(String login, String password) {
        UserBean user = new UserBean();
        user.setLogin(login);
        user.setPassword(password);
        return UsersDAO.checkUser(user);
    }

    public String defineRole(String login) {
        UserBean user = new UserBean();
        user.setLogin(login);
        return UsersDAO.defineRole(user);
    }
}
