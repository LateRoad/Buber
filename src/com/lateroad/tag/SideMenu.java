package com.lateroad.tag;

import com.lateroad.buber.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
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
            out.write("<div class=\"layout-sidebar\">");
            out.write("    <div class=\"custom-scrollbar\">");
            out.write("        <div class=\"layout-sidebar-header\">");
            out.write("            <button class=\"doc-toggler collapsed\" type=\"button\" data-toggle=\"collapse\" data-target=\"#sidenav\">");
            out.write("            <span class=\"sr-only\">Toggle navigation</span>");
            out.write("            <span class=\"bars\">");
            out.write("                <span class=\"bar-line bar-line-1out\"></span>");
            out.write("                <span class=\"bar-line bar-line-2out\"></span>");
            out.write("                <span class=\"bar-line bar-line-3out\"></span>");
            out.write("            </span>");
            out.write("            <span class=\"bars bars-x\">");
            out.write("                <span class=\"bar-line bar-line-4\"></span>");
            out.write("                <span class=\"bar-line bar-line-5\"></span>");
            out.write("            </span>");
            out.write("            </button>");
            out.write("                <a class=\"doc-version\" href=\"#\">v1.0.0</a>");
            out.write("                <a class=\"doc-brand\" href=\"\">Buber</a>");
            out.write("        </div>");
            out.write("        <div class=\"layout-sidebar-body\">");
            out.write("            <nav id=\"sidenav\" class=\"sidenav-collapse collapse\">");
            out.write("                <ul class=\"sidenav\">");

            if (user != null) {
                out.write("                <li class=\"sidenav-item\"><a href=\"/home.jsp\">" + user.getLogin() + " - " + user.getRole() + "</a></li>");
            }
            if (user == null) {
                out.write("                <li class=\"sidenav-item\">");
                out.write("                    <a href=\"/signin.jsp\"><span class=\"glyphicon glyphicon-log-in\"></span> Вход</a>");
                out.write("                </li>");
            }

            if (user != null) {
                switch (user.getRole()) {
                    case "client":
                        viewClientMenu();
                        break;
                    case "admin":
                        viewAdminMenu();
                        break;
                    case "driver":
                        viewDriverMenu();
                        break;
                }
            }
            out.write("                    <li class=\"sidenav-divider\"></li>");
            out.write("                    <li class=\"sidenav-item\"><a href=\"#\">Language</a></li>");
            out.write("                    <li class=\"sidenav-item\"><a href=\"#\">- русский</a></li>");
            out.write("                    <li class=\"sidenav-item\"><a href=\"#\">- беларускі</a></li>");
            out.write("                    <li class=\"sidenav-item\"><a href=\"#\">- english</a></li>");

            if (user != null) {
                out.write("                <li class=\"sidenav-divider\"></li>");
                out.write("                <li class=\"sidenav-item\">");
                out.write("                    <a href=\"/userServlet?action=signOut\" name=\"action\"value=\"signOut\"><span class=\"glyphicon glyphicon-log-out\"></span> Выйти</a>");
                out.write("                </li>");
            }
            out.write("                </ul>");
            out.write("            </nav>");
            out.write("        </div>");
            out.write("    </div>");
            out.write("</div><!-- /.layout-sidebar -->");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private void viewClientMenu() throws IOException {
        JspWriter out = pageContext.getOut();
        out.write("                        <li class=\"sidenav-item\"><a href=\"/home.jsp\"><i class=\"fa fa-bullhorn fa-fw\"></i> Поехать</a></li>");
        out.write("                        <li class=\"sidenav-item\"><a href=\"/profile.jsp\"><i class=\"fa fa-user fa-fw\"></i> Профиль</a></li>");
        out.write("                        <li class=\"sidenav-item\"><a href=\"/payment.jsp\"><i class=\"fa fa-credit-card fa-fw\"></i> Платежи</a></li>");
        out.write("                        <li class=\"sidenav-item\">");
        out.write("                            <a href=\"/userServlet?action=getTrips\"><i class=\"fa fa-car fa-fw\"></i> Поездки</a>");
        out.write("                        </li>");

    }

    private void viewDriverMenu() throws IOException {
        JspWriter out = pageContext.getOut();
        out.write("                        <li class=\"sidenav-item\"><a href=\"/home.jsp\">Зарабатывать</a></li>");
        out.write("                        <li class=\"sidenav-item\"><a href=\"/profile.jsp\">Профиль</a></li>");
        out.write("                        <li class=\"sidenav-item\"><a href=\"/payment.jsp\">Платежи</a></li>");
        out.write("                        <li class=\"sidenav-item\"><a href=\"/car.jsp\">Моя машина</a></li>");
        out.write("                        <li class=\"sidenav-item\"><a href=\"/userServlet?action=getTrips\">Поездки</a></li>");
    }

    private void viewAdminMenu() throws IOException {
        JspWriter out = pageContext.getOut();
        out.write("                        <li><a href=\"/users.jsp\">Пользователи</a></li>");
        out.write("                        <li><a href=\"/orders.jsp\">Заказы</a></li>");
    }
}