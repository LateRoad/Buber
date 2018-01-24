package com.lateroad.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Footer extends TagSupport {
    private short year;
    private String developer;
    private String project;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<footer class=\"sticky-footer\">");
            out.write("    <div class=\"container\">");
            out.write("        <div class=\"text-center\">");
            out.write("            <strong>" + project + "</strong>");
            out.write("            <small> Copyright © " + year + " " + developer + ".</small>");
            out.write("        </div>");
            out.write("    </div>");
            out.write("</footer>");

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

}