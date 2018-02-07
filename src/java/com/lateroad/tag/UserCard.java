package com.lateroad.tag;

import com.lateroad.buber.entity.role.User;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserCard extends BodyTagSupport {
    private User user;


    public void setUser(User user) {
        this.user = user;
    }

    public void setBodyContent(BodyContent bc) {
        super.setBodyContent(bc);
    }


    @Override
    public int doAfterBody() {
        String language = (String) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", new Locale(language));

        BodyContent bodyContent = super.getBodyContent();
        String bodyString = bodyContent.getString();

        JspWriter out = bodyContent.getEnclosingWriter();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-0\"><a href=\"#\">" + user.getName() + " " + user.getSurname() + "</a></h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body py-2 small\">");
            out.write("        <i class=\"fa fa-fw fa-thumbs-o-up\"></i> " + bundle.getString("string-reputation") + ": " + user.getReputation());
            out.write("        <br>");
            out.write("        <i class=\"fa fa-fw fa-handshake-o\"></i> " + bundle.getString("string-trips-count") + ": " + user.getTripsNumber());
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-footer small text-muted\">");
            out.write("    " + bodyString);
            out.write("    </div>");
            out.write("</div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
