package com.lateroad.tag;

import com.lateroad.buber.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SideMenu extends TagSupport {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark fixed-top\" id=\"mainNav\">");
            out.write("    <a class=\"navbar-brand\" href=\"index.jsp\"><small>b</small>Uber <small> 1.0.0</small></a>");
            out.write("        <button class=\"navbar-toggler navbar-toggler-right\" type=\"button\" data-toggle=\"collapse\"");
            out.write("            data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\"");
            out.write("            aria-label=\"Toggle navigation\">");
            out.write("            <span class=\"navbar-toggler-icon\"></span>");
            out.write("        </button>");

            out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">");
            out.write("            <ul class=\"navbar-nav navbar-sidenav\" id=\"exampleAccordion\">");

            if (user != null) {
                switch (user.getRole()) {
                    case "client":
                        clientMenu(out);
                        break;
                    case "driver":
                        driverMenu(out);
                        break;
                    case "admin":
                        adminMenu(out);
                        break;
                    default:
                        //smth went wrong
                        //log menu is broken by undefined role
                        break;
                }
            }

            out.write("                <li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Language\">");
            out.write("                    <a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#language\" data-parent=\"#language\">");
            out.write("                        <i class=\"fa fa-fw fa-language\"></i>");
            out.write("                        <span class=\"nav-link-text\">Language</span>");
            out.write("                    </a>");

            out.write("                    <ul class=\"sidenav-second-level collapse\" id=\"language\">");
            out.write("                        <li><a href=\"#\">русский</a></li>");
            out.write("                        <li><a href=\"#\">беларускi</a></li>");
            out.write("                        <li><a href=\"#\">english</a></li>");
            out.write("                    </ul>");
            out.write("                </li>");
            out.write("            </ul>");

            out.write("            <ul class=\"navbar-nav sidenav-toggler\">");
            out.write("                <li class=\"nav-item\">");
            out.write("                    <a class=\"nav-link text-center\" id=\"sidenavToggler\">");
            out.write("                        <i class=\"fa fa-fw fa-angle-left\"></i>");
            out.write("                    </a>");
            out.write("                </li>");
            out.write("            </ul>");

            out.write("            <ul class=\"navbar-nav ml-auto\">");
            if (user != null) {
                out.write("                <li class=\"nav-item\"><a href=\"/home.jsp\"  class=\"nav-link\" style=\"color: white;\">" + user.getLogin() + " - " + user.getRole() + "</a></li>");
                out.write("                <li class=\"nav-item\"></li>");
                out.write("                <li class=\"nav-item\">");
                out.write("                    <a class=\"nav-link\" data-toggle=\"modal\" data-target=\"#modal-sign-out\">");
                out.write("                        <i class=\"fa fa-fw fa-sign-out\"></i>Sign out</a>");
                out.write("                    </a>");
                out.write("                </li>");
            }
            if (user == null) {
                out.write("                <li class=\"nav-item\">");
                out.write("                    <a href=\"/signin.jsp\" class=\"nav-link\"><i class=\"fa fa-fw fa-sign-in\"></i>Sign in</a>");
                out.write("                </li>");
            }
            out.write("            </ul>");
            out.write("        </div>");
            out.write("    </nav>");

            logOutModal(out);
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    private void logOutModal(JspWriter out) throws IOException {
        out.write("<div class=\"modal fade\" id=\"modal-sign-out\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\"");
        out.write("    aria-hidden=\"true\">");
        out.write("    <div class=\"modal-dialog\" role=\"document\">");
        out.write("        <div class=\"modal-content\">");
        out.write("            <div class=\"modal-header\">");
        out.write("                <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>");
        out.write("                <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">");
        out.write("                    <span aria-hidden=\"true\">×</span>");
        out.write("                </button>");
        out.write("            </div>");
        out.write("            <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>");
        out.write("            <div class=\"modal-footer\">");
        out.write("                <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>");
        out.write("                <a class=\"btn btn-primary\" href=\"/userServlet?action=signOut\" name=\"action\"value=\"signOut\">Sign out</a>");
        out.write("            </div>");
        out.write("        </div>");
        out.write("    </div>");
        out.write("</div>");
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private void userMenu(JspWriter out) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Profile\"><a class=\"nav-link\" href=\"/profile.jsp\"><i class=\"fa fa-user fa-fw\"></i><span class=\"nav-link-text\"> Profile</span></a></li>");
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Payments\"><a class=\"nav-link\" href=\"/payment.jsp\"><i class=\"fa fa-credit-card fa-fw\"></i><span class=\"nav-link-text\"> Payments</span></a></li>");
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Orders\">");
        out.write("    <a class=\"nav-link\" href=\"/userServlet?action=getTrips\"><i class=\"fa fa-car fa-fw\"></i><span class=\"nav-link-text\"> Orders</span></a>");
        out.write("</li>");
    }

    private void clientMenu(JspWriter out) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Go\"><a class=\"nav-link\" href=\"/home.jsp\"><i class=\"fa fa-bullhorn fa-fw\"></i><span class=\"nav-link-text\"> Go!</span></a></li>");
        userMenu(out);
    }

    private void driverMenu(JspWriter out) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Work\"><a class=\"nav-link\" href=\"/home.jsp\"><span class=\"nav-link-text\"> Work!</span></a></li>");
        userMenu(out);
    }

    private void adminMenu(JspWriter out) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Users\"><a class=\"nav-link\" href=\"/users.jsp\"><span class=\"nav-link-text\"> Users</span></a></li>");
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Orders\"><a class=\"nav-link\" href=\"/orders.jsp\"><span class=\"nav-link-text\"> Orders</span></a></li>");
    }
}