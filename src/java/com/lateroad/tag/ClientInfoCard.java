package com.lateroad.tag;

import com.lateroad.buber.entity.role.Client;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class ClientInfoCard extends RequestContextAwareTag {
    private Client client;

    @Override
    protected int doStartTagInternal() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("translate", getRequestContext().getLocale());
        JspWriter out = pageContext.getOut();
        try {
            out.write("<div class=\"card mb-3\">");
            out.write("    <div class=\"card-body\">");
            out.write("        <h6 class=\"card-title mb-1\">" + bundle.getString("client-info-card-title") + "</h6>");
            out.write("    </div>");
            out.write("    <hr class=\"my-0\">");
            out.write("    <div class=\"card-body py-2 small\">");
            out.write("        <i class=\"fa fa-fw fa-thumbs-o-up\"></i> " + bundle.getString("string-reputation") + ": " + client.getReputation() + "<br>");
            out.write("        <i class=\"fa fa-fw fa-handshake-o\"></i> " + bundle.getString("string-trips-count") + ": " + client.getTripsNumber());
            out.write("    </div>");
            out.write("    <div class=\"card-body small bg-faded\">");
            out.write("        <div class=\"table-responsive\">");
            out.write("            <table class=\"table table-bordered\" width=\"100%\" cellspacing=\"0\">");
            out.write("            <tbody>");
            out.write("                <tr>");
            out.write("                    <td>" + bundle.getString("string-phone-number") + ":</td>");
            out.write("                    <td align=\"center\" id=\"clientInfo.phoneNumber\">" + client.getPhoneNumber());
            out.write("                    <td align=\"center\">");
            out.write("                        <button onclick=\"change('clientInfo.phoneNumber', 'editClientPhoneBtn')\" id=\"editClientPhoneBtn\" class=\"btn btn-primary\">" + bundle.getString("button-edit") + "</button>");
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
