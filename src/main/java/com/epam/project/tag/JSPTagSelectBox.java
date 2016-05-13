package com.epam.project.tag;

import com.epam.project.beans.lines.Line;
import com.epam.project.beans.TableBean;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class JSPTagSelectBox extends TagSupport {

    private final Logger log = Logger.getRootLogger();

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
            for (int i = 0; i < bean.getCountLines(); i++) {
                out.println("<option value=\"" + bean.getLines().get(i).getColumn(0) + "\">");
                out.println(bean.getLines().get(i).getColumn(0) + " - " + bean.getLines().get(i).getColumn(1));
                out.println("</option>");
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}