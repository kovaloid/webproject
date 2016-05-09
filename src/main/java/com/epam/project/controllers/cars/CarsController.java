package com.epam.project.controllers.cars;

import com.epam.project.beans.select_box.DriverSetBean;
import com.epam.project.beans.ResultSetBean;
import com.epam.project.database.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/CarsController")
public class CarsController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt_2 = null;
        ResultSet rs_2 = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT AUTO.ID, NUM, COLOR, MARK, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO "
                    + "JOIN KOVAL.AUTO_PERSONNEL ON PERSONNEL_ID = AUTO_PERSONNEL.ID ORDER BY AUTO.ID");

            stmt_2 = con.createStatement();
            rs_2 = stmt_2.executeQuery("SELECT ID, FIRST_NAME, LAST_NAME FROM KOVAL.AUTO_PERSONNEL");

            request.getSession().setAttribute("cars_rs", new ResultSetBean(rs));
            request.getSession().setAttribute("drivers_set", new DriverSetBean(rs_2));
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/cars.jsp").forward(request, response);
        } catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        } finally {
            pool.closeStatement(stmt_2, rs_2);
            pool.closeConnection(con, stmt, rs);
        }
    }

}