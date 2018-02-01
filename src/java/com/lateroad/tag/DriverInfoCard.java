package com.lateroad.tag;

import com.lateroad.buber.entity.User;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class DriverInfoCard extends RequestContextAwareTag {
    private User driver;

    @Override
    protected int doStartTagInternal() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("translate", getRequestContext().getLocale());
        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-1\">" + bundle.getString("driver-info-card-title") + "</h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body py-2 small\">");
            out.write("        <i class=\"fa fa-fw fa-thumbs-o-up\"></i> " + bundle.getString("string-reputation") + ": " + driver.getUserInfo().getDriverInfo().getReputation() + "<br>");
            out.write("        <i class=\"fa fa-fw fa-handshake-o\"></i> " + bundle.getString("string-trips-count") + ": " + driver.getUserInfo().getDriverInfo().getTripsNumber());
            out.write("    </div>");
            out.write("    <div class=\"card-body small bg-faded\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">");
            out.write("            <tbody>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("string-phone-number") + ":</td>");
            out.write("                    <td align=\"center\" id=\"driverInfo.phoneNumber\">" + driver.getUserInfo().getDriverInfo().getPhoneNumber());
            out.write("                    <td align=\"center\">");
            out.write("                        <button onclick=\"change('driverInfo.phoneNumber', 'editDriverPhoneBtn')\" id=\"editDriverPhoneBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("string-car-number") + ":</td>");
            out.write("                    <td align=\"center\" id=\"driverInfo.phoneNumber\">" + driver.getUserInfo().getDriverInfo().getCarNumber());
            out.write("                    <td align=\"center\">");
            out.write("                        <button onclick=\"change('carNumber', 'editCarNumberBtn')\" id=\"editCardNumberBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("            </tbody>");
            out.write("            </table>");
            out.write("        </div>");
            out.write("    </div>");
            out.write("    <div class=\"card-footer small text-muted\">" + bundle.getString("powered-by-lateroad") + "</div>");
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

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
