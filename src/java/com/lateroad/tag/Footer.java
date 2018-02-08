package com.lateroad.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Footer extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(Footer.class);

    private short year;
    private String developer;
    private String project;


    public void setYear(short year) {
        this.year = year;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setProject(String project) {
        this.project = project;
    }


    @Override
    public int doStartTag() {
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
            LOGGER.error("ERROR during displaying Footer tag.", e);
        }

        return SKIP_BODY;
    }
}