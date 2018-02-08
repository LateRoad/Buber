package com.lateroad.tag;

import com.lateroad.buber.entity.role.CommonUser;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SideMenu extends TagSupport {
    private CommonUser user;


    public void setUser(CommonUser user) {
        this.user = user;
    }


    @Override
    public int doStartTag() {
        Locale language = new Locale((String) this.pageContext.getSession().getAttribute("language"));
        ResourceBundle bundle = ResourceBundle.getBundle("translation", language);

        JspWriter out = pageContext.getOut();
        try {
            out.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark fixed-top\" id=\"mainNav\">");
            out.write("    <a class=\"navbar-brand\" href=\"index.jsp\">" + bundle.getString("brand-buber") + "</a>");
            out.write("        <button class=\"navbar-toggler navbar-toggler-right\" type=\"button\" data-toggle=\"collapse\"");
            out.write("            data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\"");
            out.write("            aria-label=\"Toggle navigation\">");
            out.write("            <span class=\"navbar-toggler-icon\"></span>");
            out.write("        </button>");

            out.write("        <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">");
            out.write("            <ul class=\"navbar-nav navbar-sidenav\" id=\"exampleAccordion\">");

            if (user != null) {
                switch (user.getRole()) {
                    case CLIENT:
                        clientMenu(out, bundle);
                        break;
                    case DRIVER:
                        driverMenu(out, bundle);
                        break;
                    case ADMIN:
                        adminMenu(out, bundle);
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
            out.write("                        <span class=\"nav-link-text\">" + bundle.getString("nav-language") + " " + language + "</span>");
            out.write("                    </a>");

            out.write("                    <ul class=\"sidenav-second-level collapse\" id=\"language\">");
            out.write("                        <li><a onclick=\"changeLang(\'ru\')\">" + bundle.getString("lang-russian") + "</a></li>");
            out.write("                        <li><a onclick=\"changeLang(\'be\')\">" + bundle.getString("lang-belarussian") + "</a></li>");
            out.write("                        <li><a onclick=\"changeLang(\'en\')\">" + bundle.getString("lang-english") + "</a></li>");
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
                out.write("                        <i class=\"fa fa-fw fa-sign-out\"></i>" + bundle.getString("nav-sign-out") + "</a>");
                out.write("                    </a>");
                out.write("                </li>");
            }
            if (user == null) {
                out.write("                <li class=\"nav-item\">");
                out.write("                    <a href=\"/signin.jsp\" class=\"nav-link\"><i class=\"fa fa-fw fa-sign-in\"></i>" + bundle.getString("nav-sign-in") + "</a>");
                out.write("                </li>");
            }
            out.write("            </ul>");
            out.write("        </div>");
            out.write("    </nav>");

            logOutModal(out, bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    private void logOutModal(JspWriter out, ResourceBundle bundle) throws IOException {
        out.write("<div class=\"modal fade\" id=\"modal-sign-out\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\"");
        out.write("    aria-hidden=\"true\">");
        out.write("    <div class=\"modal-dialog\" role=\"document\">");
        out.write("        <div class=\"modal-content\">");
        out.write("            <div class=\"modal-header\">");
        out.write("                <h5 class=\"modal-title\" id=\"exampleModalLabel\">" + bundle.getString("modal-sign-out-ready") + "</h5>");
        out.write("                <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"" + bundle.getString("button-close") + "\">");
        out.write("                    <span aria-hidden=\"true\">×</span>");
        out.write("                </button>");
        out.write("            </div>");
        out.write("            <div class=\"modal-body\">" + bundle.getString("modal-sign-out-body") + "</div>");
        out.write("            <div class=\"modal-footer\">");
        out.write("                <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">" + bundle.getString("button-cancel") + "</button>");
        out.write("                <a class=\"btn btn-primary\" href=\"/commonOperation?action=signOut\">" + bundle.getString("button-sign-out") + "</a>");
        out.write("            </div>");
        out.write("        </div>");
        out.write("    </div>");
        out.write("</div>");
    }

    private void userMenu(JspWriter out, ResourceBundle bundle) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\" " + bundle.getString("nav-profile") + "\"><a class=\"nav-link\" href=\"/profile.jsp\"><i class=\"fa fa-user fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-profile") + "</span></a></li>");
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\" " + bundle.getString("nav-payments") + "\"><a class=\"nav-link\" href=\"/payment.jsp\"><i class=\"fa fa-credit-card fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-payments") + "</span></a></li>");
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\" " + bundle.getString("nav-trips") + "\">");
        out.write("    <a class=\"nav-link\" href=\"/userOperation?action=getTrips\"><i class=\"fa fa-car fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-trips") + "</span></a>");
        out.write("</li>");
    }

    private void clientMenu(JspWriter out, ResourceBundle bundle) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Go\"><a class=\"nav-link\" href=\"/home.jsp\"><i class=\"fa fa-bullhorn fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-go") + "</span></a></li>");
        userMenu(out, bundle);
    }

    private void driverMenu(JspWriter out, ResourceBundle bundle) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Work\"><a class=\"nav-link\" href=\"/home.jsp\"><i class=\"fa fa-bullhorn fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-work") + "</span></a></li>");
        userMenu(out, bundle);
    }

    private void adminMenu(JspWriter out, ResourceBundle bundle) throws IOException {
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Orders\"><a class=\"nav-link\" href=\"/userOperation?action=getDrivers\"><i class=\"fa fa-taxi fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-drivers") + "</span></a></li>");
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Users\"><a class=\"nav-link\" href=\"/userOperation?action=getClients\"><i class=\"fa fa-group fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-clients") + "</span></a></li>");
        out.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"Orders\"><a class=\"nav-link\" href=\"/userOperation?action=getOrders\"><i class=\"fa fa-list-alt fa-fw\"></i><span class=\"nav-link-text\"> " + bundle.getString("nav-orders") + "</span></a></li>");
    }
}