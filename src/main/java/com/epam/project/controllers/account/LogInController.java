package com.epam.project.controllers.account;

import com.epam.project.beans.UserBean;
import com.epam.project.service.AccountManager;
import com.epam.project.service.constants.Account;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogInController")
public class LogInController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String successUrl = (String) session.getAttribute("successUrl");

        String login = request.getParameter(Account.LOGIN);
        String password = request.getParameter(Account.PASSWORD);

        AccountManager account = new AccountManager();
        String result = account.authenticate(login, password);
        String role = account.defineRole(login);

        if (result.equals(Account.Result.SUCCESS)) {
            session.setAttribute(Account.USER, new UserBean(login, password, role));
            session.setAttribute(Account.STATUS, Account.Status.IN);

            log.info("User '" + login + "' is logged in");
            response.sendRedirect(successUrl);
        } else {
            log.warn("User '" + login + "' has problems with login");
            request.setAttribute("result_auth", result);
            request.getRequestDispatcher("/WEB-INF/jsp/account/fail_auth.jsp").forward(request, response);
        }
    }

}
