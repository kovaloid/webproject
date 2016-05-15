package com.epam.project.controllers.routes;

import com.epam.project.beans.Table;
import com.epam.project.beans.TableBean;
import com.epam.project.beans.lines.Line;
import com.epam.project.beans.lines.RouteBean;
import com.epam.project.consts.Account;
import com.epam.project.database.dao.DAO;
import com.epam.project.database.dao.autobase.RoutesDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/RoutesController")
public class RoutesController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    private Table[] cutTable(int sizeOfOneCut, Table<RouteBean> oldTable) {
        int countOfTables = (int)((float)oldTable.getCountLines() / sizeOfOneCut);

        List<TableBean<RouteBean>> newTwoTables = new ArrayList<>();
        List<RouteBean> linesInFirstTable = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            linesInFirstTable.add(oldTable.getLines().get(i));
        }

        List<RouteBean> linesInSecondTable = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            linesInSecondTable.add(oldTable.getLines().get(i));
        }

        newTwoTables.get(0).setLines(linesInFirstTable);
        newTwoTables.get(1).setLines(linesInSecondTable);

        return new Table[2];
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO routesDAO = new RoutesDAO();
        Table routesTable = routesDAO.getAll();

        /*if(routesTable.getCountLines()>5){
            int countOfTables = (int)((float)routesTable.getCountLines() / 5);
            List<TableBean<RouteBean>> tables = new ArrayList<>();
            List<RouteBean> lines = routesTable.getLines();
            for(int i=0; i<countOfTables;i++) {
                tables.get(i).setLines(lines);
            }

        }*/
        DAO<RouteBean> bean = new RoutesDAO();
        Table<RouteBean> oldTable = bean.getAll();


        Table[] tables = cutTable(2,oldTable);


        //int countOfTables = 2;
        //Table[] tables = new Table[countOfTables];
        //tables[0] = new TableBean<RouteBean>();
        //tables[1] = new TableBean<RouteBean>();


        request.getSession().setAttribute("routes_table", routesTable);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (status == null || !status.equals(Account.Status.IN))
            response.sendError(403);
        else
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/routes.jsp").forward(request, response);
    }
}