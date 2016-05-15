package com.epam.project.controllers.account;

import com.epam.project.beans.UserBean;
import com.epam.project.utils.AccountManager;
import com.epam.project.consts.Account;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(Account.LOGIN);
        String password = request.getParameter(Account.PASSWORD);
        String repeat = request.getParameter(Account.REPEAT);

        String result = AccountManager.checkBeforeSignUp(login, password, repeat);

        if (result.equals(Account.Result.SUCCESS)) {
            AccountManager.signUp(login, password);
            log.info("User '" + login + "' is signed up");

            request.getSession().setAttribute(Account.USER, new UserBean(login, password, Account.Role.CLIENT));
            request.getSession().setAttribute(Account.STATUS, Account.Status.IN);
            log.info("User '" + login + "' is logged in");

            response.sendRedirect(request.getContextPath() + "/");
        } else {
            log.warn("User has problems with sign up");
            request.setAttribute("result_signup", result);
            request.getRequestDispatcher("/WEB-INF/jsp/account/fail_signup.jsp").forward(request, response);
        }
    }
}