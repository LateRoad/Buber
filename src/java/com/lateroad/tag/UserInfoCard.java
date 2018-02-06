package com.lateroad.tag;

import com.lateroad.buber.entity.role.User;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class UserInfoCard extends RequestContextAwareTag {
    private User user;

    @Override
    protected int doStartTagInternal() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("translate", getRequestContext().getLocale());
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
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
