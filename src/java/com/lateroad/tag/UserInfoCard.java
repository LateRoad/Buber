package com.lateroad.tag;

import com.lateroad.buber.entity.role.User;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserInfoCard extends TagSupport {
    private User user;


    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public int doStartTag() {
        String language = (String) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", new Locale(language));

        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-1\"><a href=\"\">" + user.getName() + " " + user.getSurname() + "</a></h6>");
            out.write("        <p class=\"card-text small\">" + user.getRole() + " " + user.getLogin() + "</p>");
            out.write("        <h6 class=\"card-title mb-1\">" + bundle.getString("userInfo-card-title") + "</h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body small bg-faded\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">");
            out.write("            <tbody>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("input-name-label") + ":</td>");
            out.write("                    <td align=\"center\" id=\"name\">" + user.getName() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('name', 'editNameBtn')\" id = \"editNameBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("input-surname-label") + ":</td>");
            out.write("                    <td align=\"center\" id=\"surname\">" + user.getSurname() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('surname', 'editSurnameBtn')\" id = \"editSurnameBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("input-lastname-label") + ":</td>");
            out.write("                    <td align=\"center\" id=\"lastname\">" + user.getLastname() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('lastname', 'editLastnameBtn')\" id = \"editLastnameBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("input-email-label") + ":</td>");
            out.write("                    <td align=\"center\" id=\"email\">" + user.getEmail() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('email', 'editEmailBtn')\" id = \"editEmailBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("            </tbody>");
            out.write("            </table>");
            out.write("        </div>");
            out.write("    </div>");
            out.write("    <div class=\"card-footer small text-muted\">" + bundle.getString("powered-by-lateroad") + "</div>");
            out.write("</div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
