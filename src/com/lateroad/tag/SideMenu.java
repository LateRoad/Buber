package com.lateroad.tag;

import com.lateroad.buber.logic.entity.User;

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
        try {
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
        out.write("<div class=\"nav-side-menu\">");
        out.write("    <div class=\"brand\">Клиент " + user.getLogin() + "</div>");
        out.write("    <i class=\"fa fa-bars fa-2x toggle-btn\" data-toggle=\"collapse\" data-target=\"#menu-content\"></i>");
        out.write("    <div class=\"menu-list\">");
        out.write("       <ul id=\"menu-content\" class=\"menu-content collapse out\">");

        out.write("           <li onclick=\"/home.jsp\"><a href=\"/home.jsp\"><i class=\"fa fa-bullhorn fa-lg\"></i> Поехать</a></li>");
        out.write("           <li onclick=\"/profile.jsp\"><a href=\"/profile.jsp\"><i class=\"fa fa-user fa-lg\"></i> Профиль</a></li>");
        out.write("           <li onclick=\"/payment.jsp\"><a href=\"/payment.jsp\"><i class=\"fa fa-credit-card fa-lg\"></i> Платежи</a></li>");
        out.write("           <li onclick=\"/userServlet?action=getTrips\"><a href=\"/userServlet?action=getTrips\"><i class=\"fa fa-car fa-lg\"></i> Поездки</a></li>");
        out.write("           <li onclick=\"/userServlet?action=signOut\"><a href=\"/userServlet?action=signOut\" name=\"action\" value=\"signOut\"><i class=\"fa fa-sign-out fa-lg\"></i> Выход</a></li>");
        out.write("        </ul>");
        out.write("    </div>");
        out.write("</div>");
    }

    private void viewDriverMenu() throws IOException {
        JspWriter out = pageContext.getOut();
        out.write("<div class=\"nav-side-menu\">");
        out.write("    <div class=\"brand\">Водитель " + user.getLogin() + "</div>");
        out.write("    <i class=\"fa fa-bars fa-2x toggle-btn\" data-toggle=\"collapse\" data-target=\"#menu-content\"></i>");
        out.write("    <div class=\"menu-list\">");
        out.write("       <ul id=\"menu-content\" class=\"menu-content collapse out\">");
        out.write("           <li><a href=\"/home.jsp\"><i class=\"fa fa-taxi fa-lg\"></i> Зарабатывать</a></li>");
        out.write("           <li><a href=\"/profile.jsp\"><i class=\"fa fa-user fa-lg\"></i> Профиль</a></li>");
        out.write("           <li><a href=\"/payment.jsp\"><i class=\"fa fa-credit-card fa-lg\"></i> Платежи</a></li>");
        out.write("           <li><a href=\"/car.jsp\"><i class=\"fa fa-car fa-lg\"></i> Моя машина</a></li>");
        out.write("           <li><a href=\"/userServlet?action=getTrips\"><i class=\"fa fa-money fa-lg\"></i> Поездки</a></li>");
        out.write("           <li><a href=\"/userServlet?action=signOut\" name=\"action\" value=\"signOut\"><i class=\"fa fa-sign-out fa-lg\"></i> Выход</a></li>");
        out.write("        </ul>");
        out.write("    </div>");
        out.write("</div>");
    }

    private void viewAdminMenu() throws IOException {
        JspWriter out = pageContext.getOut();
        out.write("<div class=\"nav-side-menu\">");
        out.write("    <div class=\"brand\">Админ " + user.getLogin() + "</div>");
        out.write("    <i class=\"fa fa-bars fa-2x toggle-btn\" data-toggle=\"collapse\" data-target=\"#menu-content\"></i>");
        out.write("    <div class=\"menu-list\">");
        out.write("            <li><a href=\"/users.jsp\"><i class=\"fa fa-users fa-lg\"></i> Пользователи</a></li>");
        out.write("            <li><a href=\"/orders.jsp\"><i class=\"fa fa-users fa-lg\"></i> Заказы</a></li>");
        out.write("           <li><a href=\"/userServlet?action=signOut\" name=\"action\" value=\"signOut\"><i class=\"fa fa-sign-out fa-lg\"></i> Выход</a></li>");
        out.write("        </ul>");
        out.write("    </div>");
        out.write("</div>");
    }
}