package com.lateroad.tag;

import com.lateroad.buber.entity.role.User;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ActiveOrder extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(ActiveOrder.class);

    private User client;
    private int orderID;


    public void setClient(User client) {
        this.client = client;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    @Override
    public int doStartTag() {
        String language = (String) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", new Locale(language));

        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-0\"><a href=\"#\">" + client.getName() + " " + client.getSurname() + "</a></h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body py-2 small\">");
            out.write("        <i class=\"fa fa-fw fa-thumbs-o-up\"></i> " + bundle.getString("string-reputation") + ": " + client.getReputation());
            out.write("        <br>");
            out.write("        <i class=\"fa fa-fw fa-handshake-o\"></i> " + bundle.getString("string-trips-count") + ": " + client.getTripsNumber());
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-footer small text-muted\">");
            out.write("        <button onclick=\"acceptOrder(" + orderID + ")\" type=\"submit\" class=\"btn btn-primary\">");
            out.write("            " + bundle.getString("accept-order"));
            out.write("        </button>");
            out.write("    </div>");
            out.write("</div>");
        } catch (IOException e) {
            LOGGER.error("ERROR during displaying ActiveOrder tag.", e);
        }
        return SKIP_BODY;
    }
}
