package com.epam.project.tag;

import com.epam.project.beans.lines.Line;
import com.epam.project.beans.TableBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This tag print table on JSP.
 *
 * @author Artem Kovalev
 * @version 1.0
 */
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

    /**
     * Main method of tag class.
     */
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

    /**
     * Method get simple database header and convert it to local header.
     *
     * @param headerDataBaseName header string from database
     * @param locale             locale string
     * @return locale header string
     */
    private String getLocaleHeader(String headerDataBaseName, String locale) {
        if (locale == null) locale = "en";
        ResourceBundle bundle = ResourceBundle.getBundle("locale", new Locale(locale));
        switch (headerDataBaseName) {
            case "CAR_NUMBER":
                return bundle.getString("local.table.CAR_NUMBER");
            case "COLOR":
                return bundle.getString("local.table.COLOR");
            case "BRAND":
                return bundle.getString("local.table.BRAND");
            case "READY":
                return bundle.getString("local.table.READY");
            case "NAME":
                return bundle.getString("local.table.NAME");
            case "SURNAME":
                return bundle.getString("local.table.SURNAME");
            case "GENDER":
                return bundle.getString("local.table.GENDER");
            case "PHONE":
                return bundle.getString("local.table.PHONE");
            case "DATE_OUT":
                return bundle.getString("local.table.DATE_OUT");
            case "DATE_IN":
                return bundle.getString("local.table.DATE_IN");
            case "ROUTE_NAME":
                return bundle.getString("local.table.ROUTE_NAME");
            case "LENGTH":
                return bundle.getString("local.table.LENGTH");
            case "PRICE":
                return bundle.getString("local.table.PRICE");
            default:
                return headerDataBaseName;
        }
    }

    /**
     * Method make html table header string.
     *
     * @return html table header string
     */
    private String getTableHeader() {
        StringBuilder header = new StringBuilder();
        header.append("\t\t<tr>\n");
        for (int i = 0; i < bean.getCountColumns(); i++) {
            header.append("\t\t\t<th>").append(getLocaleHeader(bean.getHeaders().get(i), locale)).append("</th>");
        }
        header.append("\n\t\t</tr>\n");
        return header.toString();
    }

    /**
     * Method make html table body string.
     *
     * @return html table body string
     */
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
