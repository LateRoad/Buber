package com.lateroad.tag;

import com.lateroad.buber.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DriverInfoCard extends TagSupport{
    private User driver;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-1\">Driver Info</h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body py-2 small\">");
            out.write("        <i class=\"fa fa-fw fa-thumbs-o-up\"></i> Reputation: " + driver.getUserInfo().getDriverInfo().getReputation() + "<br>");
            out.write("        <i class=\"fa fa-fw fa-handshake-o\"></i> Trips count: " + driver.getUserInfo().getDriverInfo().getTripsNumber());
            out.write("    </div>");
            out.write("    <div class=\"card-body small bg-faded\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">");
            out.write("            <tbody>");
            out.write("                <tr>");
            out.write("                    <td>Phone number:</td>");
            out.write("                    <td align=\"center\" id=\"driverInfo.phoneNumber\">" + driver.getUserInfo().getDriverInfo().getPhoneNumber());
            out.write("                    <td align=\"center\">");
            out.write("                        <button onclick=\"change('driverInfo.phoneNumber', 'editDriverPhoneBtn')\" id=\"editDriverPhoneBtn\" class=\"btn btn-primary\">Edit</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>Car number:</td>");
            out.write("                    <td align=\"center\" id=\"driverInfo.phoneNumber\">" + driver.getUserInfo().getDriverInfo().getCarNumber());
            out.write("                    <td align=\"center\">");
            out.write("                        <button onclick=\"change('carNumber', 'editCarNumberBtn')\" id=\"editCardNumberBtn\" class=\"btn btn-primary\">Edit</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("            </tbody>");
            out.write("            </table>");
            out.write("        </div>");
            out.write("    </div>");
            out.write("    <div class=\"card-footer small text-muted\">Editable table</div>");
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
