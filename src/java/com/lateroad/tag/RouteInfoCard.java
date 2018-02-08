package com.lateroad.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class RouteInfoCard extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(RouteInfoCard.class);

    private String price;
    private String distance;
    private String time;


    public void setPrice(String price) {
        this.price = price;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public int doStartTag() {
        String language = (String) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", new Locale(language));

        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-header\">");
            out.write("        <i class=\"fa fa-location-arrow\"></i> Route info");
            out.write("    </div>");
            out.write("    <div class=\"card-body\">");
            out.write("        " + bundle.getString("route-info-distance") + ": " + distance);
            out.write("        <br>");
            out.write("        " + bundle.getString("route-info-travelTime") + ": " + time);
            out.write("        <br>");
            out.write("        " + bundle.getString("route-info-price") + ": " + price);
            out.write("        <br>");
            out.write("    </div>");
            out.write("</div>");
        } catch (IOException e) {
            LOGGER.error("ERROR during displaying RouteInfoCard tag.");
        }
        return SKIP_BODY;
    }
}
