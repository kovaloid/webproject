package com.epam.project.service;

import com.epam.project.beans.ResultSetBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class JSPTagPrintTable extends TagSupport {

    private ResultSetBean bean;

    public ResultSetBean getBean() {
        return bean;
    }

    public void setBean(ResultSetBean bean) {
        this.bean = bean;
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

    private String getTableHeader() {
        StringBuilder str = new StringBuilder();
        str.append("\t\t<tr>\n");
        for (int i = 0; i < bean.getColumnCount(); i++) {
            str.append("\t\t\t<th>").append(bean.getColumnName(i+1)).append("</th>");
        }
        str.append("\n\t\t</tr>\n");
        return str.toString();
    }

    private String getTableBody() {
        StringBuilder str = new StringBuilder();
        while (bean.getNext()) {
            str.append("\t\t<tr>\n");
            for (int i = 0; i < bean.getColumnCount(); i++) {
                str.append("\t\t\t<td>").append(bean.getLine()[i]).append("</td>");
            }
            str.append("\n\t\t</tr>\n");
        }
        return str.toString();
    }

}
