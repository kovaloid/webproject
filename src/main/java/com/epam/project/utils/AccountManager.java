package com.epam.project.utils;

import com.epam.project.beans.Table;
import com.epam.project.beans.UserBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.UsersDAO;
import com.epam.project.consts.Account;

import java.util.List;

/**
 * This util class help to auth and sign up users.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
public class AccountManager {

    /**
     * This method checks: login string in parameter contains in database or not.
     *
     * @param login user's login
     * @return true if not contains and false if contains
     */
    private static boolean checkSameLogin(String login) {
        DAO<UserBean> usersDAO = new UsersDAO();
        Table<UserBean> usersTable = usersDAO.getAll();
        List<UserBean> lines = usersTable.getLines();
        String current_login = login.trim();
        for (int i = 0; i < usersTable.getCountLines(); i++) {
            if ((current_login.equalsIgnoreCase(lines.get(i).getLogin())))
                return false;
        }
        return true;
    }

    /**
     * This method validates login.
     *
     * @param login user's login
     * @return true if bad and false if good
     */
    private static boolean isBadLogin(String login) {
        boolean condition_1 = (login.length() < 20) && (login.length() > 3);
        boolean condition_2 = checkSameLogin(login);
        // Имя пользователя (с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ обязательно буква)
        //boolean condition_3 = login.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
        //return !(condition_1 && condition_2 && condition_3);
        return !(condition_1 && condition_2);
    }

    /**
     * This method validates password.
     *
     * @param password user's password
     * @return true if bad and false if good
     */
    private static boolean isBadPassword(String password) {
        boolean condition_1 = (password.length() < 20) && (password.length() > 3);
        //Пароль (Строчные и прописные латинские буквы, цифры):
        //boolean condition_2 = password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");
        //return !(condition_1 && condition_2);
        return !(condition_1);
    }

    /**
     * This method validates repeat of password.
     *
     * @param password repeat of password
     * @return true if bad and false if good
     */
    private static boolean isBadRepeat(String password, String repeat) {
        return !password.equals(repeat);
    }

    /**
     * This method checks login and password of user before sign up.
     *
     * @param login user's login
     * @param password user's password
     * @param repeat repeat of password
     * @return success or fail constant
     */
    public static String checkBeforeSignUp(String login, String password, String repeat) {
        boolean bad_login = isBadLogin(login);
        boolean bad_password = isBadPassword(password);
        boolean bad_repeat = isBadRepeat(password, repeat);
        if (bad_login || bad_password || bad_repeat) {
            if (bad_login) return Account.Result.FAIL_LOGIN;
            if (bad_password) return Account.Result.FAIL_PASSWORD;
            if (bad_repeat) return Account.Result.FAIL_REPEAT;
        }
        return Account.Result.SUCCESS;
    }

    /**
     * This method signs up user account in system.
     *
     * @param login user's login
     * @param password user's password
     * @return success or fail constant
     */
    public static void signUp(String login, String password) {
        UserBean user = new UserBean();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(Account.Role.CLIENT);
        DAO<UserBean> usersDAO = new UsersDAO();
        usersDAO.add(user);
    }

    /**
     * This method checks login and password of user before login.
     *
     * @param login user's login
     * @param password user's password
     * @return success or fail constant
     */
    public static String checkBeforeAuth(String login, String password) {
        DAO<UserBean> usersDAO = new UsersDAO();
        Table<UserBean> usersTable = usersDAO.getAll();
        List<UserBean> lines = usersTable.getLines();
        String current_login = login.trim();
        String current_password = password.trim();
        String result = null;
        for (int i = 0; i < usersTable.getCountLines(); i++) {
            if (current_login.equalsIgnoreCase(lines.get(i).getLogin())) {
                if (current_password.equals(lines.get(i).getPassword())) {
                    return Account.Result.SUCCESS;
                } else {
                    return Account.Result.FAIL_PASSWORD;
                }
            } else {
                result = Account.Result.FAIL_LOGIN;
            }
        }
        return result;
    }

    /**
     * This method define user's role by its login.
     *
     * @param login user's login
     * @return admin or client constant
     */
    public static String defineRole(String login) {
        DAO<UserBean> usersDAO = new UsersDAO();
        Table<UserBean> usersTable = usersDAO.getAll();
        List<UserBean> lines = usersTable.getLines();
        String current_login = login.trim();
        for (int i = 0; i < usersTable.getCountLines(); i++) {
            if (current_login.equalsIgnoreCase(lines.get(i).getLogin())) {
                String role = lines.get(i).getRole();
                if (role == null) {
                    return Account.Role.CLIENT;
                } else if (role.trim().equalsIgnoreCase(Account.Role.ADMIN)) {
                    return Account.Role.ADMIN;
                }
            }
        }
        return Account.Role.CLIENT;
    }
}
