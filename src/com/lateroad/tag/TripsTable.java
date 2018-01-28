package com.lateroad.tag;

import com.lateroad.buber.entity.Order;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class TripsTable extends TagSupport {
    private String role;
    private List<Order> trips;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-header\"><i class=\"fa fa-table\"></i> Your trips</div>");
            out.write("    <div class=\"card-body\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">");
            out.write("                <thead>");
            out.write("                <tr>");
            if ("client".equals(role)) {
                out.write("                <th>Your driver</th>");
            } else if ("driver".equals(role)) {
                out.write("                <th>Your client</th>");
            }
            out.write("                    <th>Price</th>");
            out.write("                    <th>Date</th>");
            out.write("                    <th>Status</th>");
            out.write("                </tr>");
            out.write("                </thead>");
            out.write("                <tfoot>");
            out.write("                <tr>");
            if ("client".equals(role)) {
                out.write("                <th>Your driver</th>");
            } else if ("driver".equals(role)) {
                out.write("                <th>Your client</th>");
            }
            out.write("                    <th>Price</th>");
            out.write("                    <th>Date</th>");
            out.write("                    <th>Status</th>");
            out.write("                </tr>");
            out.write("                </tfoot>");
            out.write("                <tbody>");
            for (Order trip : trips) {
                out.write("            <tr>");
                if ("client".equals(role)) {
                    out.write("            <td>" + trip.getDriverLogin() + "</td>");
                } else if ("driver".equals(role)) {
                    out.write("            <td>" + trip.getClientLogin() + "</td>");
                }

                out.write("                <td>" + trip.getMoney() + "</td>");
                out.write("                <td>" + trip.getDate() + "</td>");
                switch (trip.getStatus()) {
                    case DONE:
                        out.write("        <td>Выполнено</td>");
                        break;
                    case UNDONE:
                        out.write("        <td>Активно</td>");
                        break;
                    case CANCELLED:
                        out.write("        <td>Отменено</td>");
                        break;
                    default:
                        //log
                        break;
                }
                out.write("            </tr>");
            }
            out.write("                </tbody>");
            out.write("            </table>");
            out.write("        </div>");
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Order> getTrips() {
        return trips;
    }

    public void setTrips(List<Order> trips) {
        this.trips = trips;
    }
}