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
        Logger log = Logger.getRootLogger();

        int countOfNewTables = (int) ((float) oldTable.getCountLines() / sizeOfOneCut);

        log.info(countOfNewTables);

        Table[] newTables = (TableBean<RouteBean>[])new TableBean[countOfNewTables];
        List<RouteBean>[] linesInNewTables = (ArrayList<RouteBean>[])new ArrayList[sizeOfOneCut];
        for (int i = 0; i < countOfNewTables; i++) {
            newTables[i] = new TableBean<RouteBean>();
            linesInNewTables[i] = new ArrayList<>();
        }

        int tableNum = 0;
        for (int i = 0; i < countOfNewTables; i++) {
            for (int j = 0; j < sizeOfOneCut; j++) {
                linesInNewTables[i].add(oldTable.getLines().get(tableNum * sizeOfOneCut + j));
            }
        }

        for (int i = 0; i < countOfNewTables; i++) {
            newTables[i].setLines(linesInNewTables[i]);
        }

        log.info(newTables[0].getLines().get(0));

        return newTables;
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


        Logger log = Logger.getRootLogger();
        Table[] tables = cutTable(2, oldTable);
        log.info(tables[0].getCountColumns());
        log.info(tables[0].getCountLines());
        //log.info(tables[0].getLines().get(2));




        request.getSession().setAttribute("routes_table", tables[0]);
        //request.getSession().setAttribute("routes_table", routesTable);

        String status = (String) request.getSession().getAttribute(Account.STATUS);
        if (status == null || !status.equals(Account.Status.IN))
            response.sendError(403);
        else
            request.getRequestDispatcher("/WEB-INF/jsp/data_tables/routes.jsp").forward(request, response);
    }
}