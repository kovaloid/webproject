package com.epam.project.controllers.account;

import com.epam.project.beans.UserBean;
import com.epam.project.consts.Account;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LogOut Controller.
 * <p>This controller sets attribute 'status' at 'out'.
 * Then there is removal of user bean.</p>
 *
 * @author Artem Kovalev
 * @version 1.0
 */
@WebServlet("/LogOutController")
public class LogOutController extends HttpServlet {
    private final static Logger log = Logger.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.setAttribute(Account.STATUS, Account.Status.OUT);
        log.info("User '" + ((UserBean) session.getAttribute(Account.USER)).getLogin() + "' is logged out");
        session.removeAttribute(Account.USER);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
