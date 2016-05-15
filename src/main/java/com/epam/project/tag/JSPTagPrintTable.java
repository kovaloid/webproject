package com.epam.project.tag;

import com.epam.project.beans.lines.Line;
import com.epam.project.beans.TableBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class JSPTagPrintTable extends TagSupport {
    private TableBean<Line> bean;
    private String locale;

    public TableBean<Line> getBean() {
        return bean;
    }

    public void setBean(TableBean<Line> bean) {
        this.bean = bean;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.println("<table class=\"table table-striped\">");
            out.println("\t<thead>");
            out.println(getTableHeader());
            out.println("\t</thead>");
            out.println("\t<tbody>");
            out.println(getTableBody());
            out.println("\t</tbody>");
            out.println("</table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    private String getLocaleHeader(String headerDataBaseName, String locale) {
        if (locale.equalsIgnoreCase("ru")) {
            switch (headerDataBaseName) {
                case "CAR_NUMBER":
                    return "Номер машины";
                case "COLOR":
                    return "Цвет";
                case "BRAND":
                    return "Марка";
                case "READY":
                    return "Исправность";
                case "NAME":
                    return "Имя";
                case "SURNAME":
                    return "Фамилия";
                case "GENDER":
                    return "Пол";
                case "PHONE":
                    return "Телефон";
                case "DATE_OUT":
                    return "Дата отбытия";
                case "DATE_IN":
                    return "Дата прибытия";
                case "ROUTE_NAME":
                    return "Маршрут";
                case "LENGTH":
                    return "Длина пути";
                case "PRICE":
                    return "Цена";
                default:
                    return headerDataBaseName;
            }
        } else {
            switch (headerDataBaseName) {
                case "CAR_NUMBER":
                    return "Car number";
                case "COLOR":
                    return "Color";
                case "BRAND":
                    return "Brand";
                case "READY":
                    return "Is ready";
                case "NAME":
                    return "Name";
                case "SURNAME":
                    return "Surname";
                case "GENDER":
                    return "Gender";
                case "PHONE":
                    return "Phone";
                case "DATE_OUT":
                    return "Date out";
                case "DATE_IN":
                    return "Date in";
                case "ROUTE_NAME":
                    return "Route name";
                case "LENGTH":
                    return "Length";
                case "PRICE":
                    return "Price";
                default:
                    return headerDataBaseName;
            }
        }
    }

    private String getTableHeader() {
        StringBuilder header = new StringBuilder();
        header.append("\t\t<tr>\n");
        for (int i = 0; i < bean.getCountColumns(); i++) {
            header.append("\t\t\t<th>").append(getLocaleHeader(bean.getHeaders().get(i), locale)).append("</th>");
        }
        header.append("\n\t\t</tr>\n");
        return header.toString();
    }

    private String getTableBody() {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < bean.getCountLines(); i++) {
            body.append("\t\t<tr>\n");
            for (int j = 0; j < bean.getCountColumns(); j++) {
                body.append("\t\t\t<td>").append(bean.getLines().get(i).getColumn(j)).append("</td>");
            }
            body.append("\n\t\t</tr>\n");
        }
        return body.toString();
    }
}
