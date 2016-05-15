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

    public TableBean<Line> getBean() {
        return bean;
    }

    public void setBean(TableBean<Line> bean) {
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
        StringBuilder header = new StringBuilder();
        header.append("\t\t<tr>\n");
        for (int i = 0; i < bean.getCountColumns(); i++) {
            header.append("\t\t\t<th>").append(bean.getHeaders().get(i)).append("</th>");
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
