package com.epam.project.controllers.journal.data_controls;

import com.epam.project.beans.lines.JournalBean;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.JournalDAO;
import com.epam.project.service.DateMaker;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/UpdateJournalController")
public class UpdateJournalController extends HttpServlet {

    private final static Logger log = Logger.getRootLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.takeConnection();
        Statement stmt = null;
        try {
            String day_in = request.getParameter("day_in");
            String month_in = request.getParameter("month_in");
            String year_in = request.getParameter("year_in");
            String date_in = DateMaker.make(day_in, month_in, year_in);

            stmt = con.createStatement();
            int rows = stmt.executeUpdate("UPDATE KOVAL.JOURNAL SET TIME_IN='" + date_in + "' WHERE ID='" + request.getParameter("id") + "'");

            log.info(rows + " row(s) was updated");
            request.getRequestDispatcher("JournalController").forward(request, response);
        } catch (SQLException e) {
            //log.error(e.getMessage());
            //request.setAttribute("exception", e.getMessage());
            //request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);

            throw new RuntimeException("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");

            //response.sendError(404, "12");
        } finally {
            pool.closeConnection(con, stmt);
        }*/



        try {
            DAO<JournalBean> dao = new JournalDAO();

            String day_in = request.getParameter("day_in");
            String month_in = request.getParameter("month_in");
            String year_in = request.getParameter("year_in");

            Date date_in = DateMaker.make(day_in, month_in, year_in);

            Integer id = Integer.valueOf(request.getParameter("id"));

            dao.update(new JournalBean(id, date_in));
            request.getRequestDispatcher("JournalController").forward(request, response);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/errors/exception.jsp").forward(request, response);
        }










    }
}