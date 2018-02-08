package com.lateroad.tag;

import com.lateroad.buber.entity.Order;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TripsTable extends TagSupport {
    private List<Order> trips;


    public void setTrips(List<Order> trips) {
        this.trips = trips;
    }


    @Override
    public int doStartTag() {
        String language = (String) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", new Locale(language));

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
                out.write("                    <th>" + bundle.getString("driver") + "</th>");
                out.write("                    <th>" + bundle.getString("client") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-price") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-date") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-status") + "</th>");
                out.write("                    <th>" + bundle.getString("actions") + "</th>");
                out.write("                </tr>");
                out.write("                </thead>");
                out.write("                <tfoot>");
                out.write("                <tr>");
                out.write("                    <th>" + bundle.getString("driver") + "</th>");
                out.write("                    <th>" + bundle.getString("client") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-price") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-date") + "</th>");
                out.write("                    <th>" + bundle.getString("trips-table-order-status") + "</th>");
                out.write("                    <th>" + bundle.getString("actions") + "</th>");
                out.write("                </tr>");
                out.write("                </tfoot>");
                out.write("                <tbody>");
                for (Order trip : trips) {
                    out.write("            <tr>");
                    out.write("                <td>" + trip.getDriverLogin() + "</td>");
                    out.write("                <td>" + trip.getClientLogin() + "</td>");


                    out.write("                <td>" + trip.getMoney() + "</td>");
                    out.write("                <td>" + trip.getDate() + "</td>");
                    switch (trip.getStatus()) {
                        case DONE:
                            out.write("        <td>" + bundle.getString("order-status-done") + "</td>");
                            out.write("        <td></td>");
                            break;
                        case UNDONE:
                            out.write("        <td>" + bundle.getString("order-status-active") + "</td>");
                            out.write("        <td>");
                            out.write("            <button onclick=\"setOrderStatus(" + trip.getId() + ", \'CANCELLED\')\" type=\"button\"\n class=\"btn btn-primary\">" + bundle.getString("button-cancel") + "</button>\n");
                            out.write("        </td>");
                            break;
                        case CANCELLED:
                            out.write("        <td>" + bundle.getString("order-status-cancelled") + "</td>");
                            out.write("        <td></td>");
                            break;
                        default:
                            //log
                            break;
                    }
                    out.write("            </tr>");
                    out.write("        </tbody>");
                }
            } else {
                out.write("                <td>" + "Не сделано ни одной поездки." + "</td>");
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