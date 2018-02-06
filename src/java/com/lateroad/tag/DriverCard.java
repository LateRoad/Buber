package com.lateroad.tag;

import com.lateroad.buber.entity.role.Driver;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class DriverCard extends RequestContextAwareTag {
    private Driver driver;
    private String priceForOrder;

    @Override
    protected int doStartTagInternal() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("translate", getRequestContext().getLocale());
        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-0\"><a href=\"#\">" + driver.getName() + " " + driver.getSurname() + "</a></h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body py-2 small\">");
            out.write("        <i class=\"fa fa-fw fa-thumbs-o-up\"></i> " + bundle.getString("string-reputation") + ": " + driver.getReputation());
            out.write("        <br>");
            out.write("        <i class=\"fa fa-fw fa-handshake-o\"></i> " + bundle.getString("string-trips-count") + ": " + driver.getTripsNumber());
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-footer small text-muted\">");
            out.write("        <button onclick=\"takeTaxi('" + driver.getLogin() + "', '" + priceForOrder + "')\" type=\"button\"class=\"btn btn-primary\">" + bundle.getString("button-take-taxi") + "</button>");
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getPriceForOrder() {
        return priceForOrder;
    }

    public void setPriceForOrder(String priceForOrder) {
        this.priceForOrder = priceForOrder;
    }
}
