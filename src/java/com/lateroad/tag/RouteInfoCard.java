package com.lateroad.tag;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class RouteInfoCard extends RequestContextAwareTag {
    private String price;
    private String distance;
    private String time;


    @Override
    protected int doStartTagInternal() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("translate", getRequestContext().getLocale());
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
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
