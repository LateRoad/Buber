package com.lateroad.tag;

import com.lateroad.buber.entity.role.Driver;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DriverInfoCard extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(DriverInfoCard.class);

    private Driver driver;


    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    @Override
    public int doStartTag() {
        String language = (String) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", new Locale(language));

        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-1\">" + bundle.getString("driver-info-card-title") + "</h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body py-2 small\">");
            out.write("        <i class=\"fa fa-fw fa-thumbs-o-up\"></i> " + bundle.getString("string-reputation") + ": " + driver.getReputation() + "<br>");
            out.write("        <i class=\"fa fa-fw fa-handshake-o\"></i> " + bundle.getString("string-trips-count") + ": " + driver.getTripsNumber());
            out.write("    </div>");
            out.write("    <div class=\"card-body small bg-faded\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">");
            out.write("            <tbody>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("string-phone-number") + ":</td>");
            out.write("                    <td align=\"center\" id=\"driverInfo.phoneNumber\">" + driver.getPhoneNumber());
            out.write("                    <td align=\"center\">");
            out.write("                        <button onclick=\"change('driverInfo.phoneNumber', 'editDriverPhoneBtn')\" id=\"editDriverPhoneBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("string-car-number") + ":</td>");
            out.write("                    <td align=\"center\" id=\"driverInfo.phoneNumber\">" + driver.getCarNumber());
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
            LOGGER.error("ERROR during displaying DriverInfoCard tag.", e);
        }
        return SKIP_BODY;
    }
}
