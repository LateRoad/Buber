package com.lateroad.tag;

import com.lateroad.buber.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserInfoCard extends TagSupport {
    private User user;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-1\"><a href=\"\">" + user.getUserInfo().getName() + user.getUserInfo().getSurname() + "</a></h6>");
            out.write("        <p class=\"card-text small\">" + user.getRole() + " " + user.getLogin() + "</p>");
            out.write("        <h6 class=\"card-title mb-1\">User Info</h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body small bg-faded\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">");
            out.write("            <tbody>");
            out.write("                <tr>");
            out.write("                    <td>Name:</td>");
            out.write("                    <td align=\"center\" id=\"name\">" + user.getUserInfo().getName() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('name', 'editNameBtn')\" id = \"editNameBtn\" class=\"btn btn-primary\">Edit</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>Surname:</td>");
            out.write("                    <td align=\"center\" id=\"surname\">" + user.getUserInfo().getSurname() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('surname', 'editSurnameBtn')\" id = \"editSurnameBtn\" class=\"btn btn-primary\">Edit</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>Lastname:</td>");
            out.write("                    <td align=\"center\" id=\"lastname\">" + user.getUserInfo().getLastname() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('lastname', 'editLastnameBtn')\" id = \"editLastnameBtn\" class=\"btn btn-primary\">Edit</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("                <tr>");
            out.write("                    <td>E-mail:</td>");
            out.write("                    <td align=\"center\" id=\"email\">" + user.getUserInfo().getEmail() + "</td>");
            out.write("                    <td align = \"center\">");
            out.write("                        <button onclick = \"change('email', 'editEmailBtn')\" id = \"editEmailBtn\" class=\"btn btn-primary\">Edit</button>");
            out.write("                    </td>");
            out.write("                </tr>");
            out.write("            </tbody>");
            out.write("            </table>");
            out.write("        </div>");
            out.write("    </div>");
            out.write("    <div class=\"card-footer small text-muted\">Editable table</div>");
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
