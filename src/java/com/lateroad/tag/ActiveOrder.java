package com.lateroad.tag;

import com.lateroad.buber.entity.role.User;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class ActiveOrder extends RequestContextAwareTag {
    private User client;
    private int orderID;

    @Override
    protected int doStartTagInternal() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("translate", getRequestContext().getLocale());
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
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
