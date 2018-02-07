package com.lateroad.tag;

import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.type.UserType;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TripsTable extends TagSupport {
    private UserType role;
    private List<Order> trips;


    public void setRole(UserType role) {
        this.role = role;
    }

    public void setTrips(List<Order> trips) {
        this.trips = trips;
    }


    @Override
    public int doStartTag() {
        Locale language = (Locale) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", language);

        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-header\"><i class=\"fa fa-table\"></i> " + bundle.getString("trips-table-yo-trips") + "</div>");
            out.write("    <div class=\"card-body\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">");

            if (trips != null) {
                out.write("                <thead>");
                out.write("                <tr>");
                if (UserType.CLIENT.equals(role)) {
                    out.write("                <th>" + bundle.getString("trips-table-yo-driver") + "</th>");
                } else if (UserType.DRIVER.equals(role)) {
                    out.write("                <th>" + bundle.getString("trips-table-yo-client") + "</th>");
                }
                out.write("                    <th>" + bundle.getString("trips-table-order-price") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-date") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-status") + "</th>");
                out.write("                </tr>");
                out.write("                </thead>");
                out.write("                <tfoot>");
                out.write("                <tr>");
                if (UserType.CLIENT.equals(role)) {
                    out.write("                <th>" + bundle.getString("trips-table-yo-driver") + "</th>");
                } else if (UserType.DRIVER.equals(role)) {
                    out.write("                <th>" + bundle.getString("trips-table-yo-client") + "</th>");
                }
                out.write("                    <th>" + bundle.getString("trips-table-order-price") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-date") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-status") + "</th>");
                out.write("                </tr>");
                out.write("                </tfoot>");
                out.write("                <tbody>");
                for (Order trip : trips) {
                    out.write("            <tr>");
                    if (UserType.CLIENT.equals(role)) {
                        out.write("            <td>" + trip.getDriverLogin() + "</td>");
                    } else if (UserType.DRIVER.equals(role)) {
                        out.write("            <td>" + trip.getClientLogin() + "</td>");
                    }

                    out.write("                <td>" + trip.getMoney() + "</td>");
                    out.write("                <td>" + trip.getDate() + "</td>");
                    switch (trip.getStatus()) {
                        case DONE:
                            out.write("        <td>" + bundle.getString("order-status-done") + "</td>");
                            break;
                        case UNDONE:
                            out.write("        <td>" + bundle.getString("order-status-active") + "</td>");
                            break;
                        case CANCELLED:
                            out.write("        <td>" + bundle.getString("order-status-cancelled") + "</td>");
                            break;
                        default:
                            //log
                            break;
                    }
                    out.write("            </tr>");
                    out.write("        </tbody>");
                }
            } else {
                out.write("                <td>" + "Вы еще не сделали ни одной поездки" + "</td>");
            }

            out.write("            </table>");
            out.write("        </div>");
            out.write("    </div>");
            out.write("</div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}