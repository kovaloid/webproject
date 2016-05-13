package com.epam.project.controllers.cars.data_controls;

import com.epam.project.beans.lines.CarBean;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.CarsDAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddCarsController")
public class AddCarsController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            int rows = stmt.executeUpdate("INSERT INTO KOVAL.AUTO(NUM,MARK,COLOR,PERSONNEL_ID) VALUES('" + request.getParameter("num") + "','" + request.getParameter("mark") + "','" + request.getParameter("color") + "','" + request.getParameter("driver") + "')");

            log.info(rows + " row(s) was inserted");
            request.getRequestDispatcher("CarsController").forward(request, response);
        } catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        } finally {
            pool.closeConnection(con, stmt);
        }*/

        DAO<CarBean> dao = new CarsDAO();

        String number = request.getParameter("num");
        String mark = request.getParameter("mark");
        String color = request.getParameter("color");
        String driverId = request.getParameter("driver");

        dao.add(new CarBean(number, mark, color, driverId));
        request.getRequestDispatcher("CarsController").forward(request, response);
    }
}