package com.lateroad.tag;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class SuccessOrderModal extends RequestContextAwareTag {
    @Override
    protected int doStartTagInternal() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("translate", getRequestContext().getLocale());
        JspWriter out = pageContext.getOut();
        try {
            out.write("<!--Modal -->");
            out.write("<div id=\"successOrder\"class=\"modal fade\"role=\"dialog\">");
            out.write("    <div class=\"modal-dialog\">");
            out.write("    <!--Modal content-->");
            out.write("        <div class=\"modal-content\">");
            out.write("            <div class=\"modal-header\">");
            out.write("                <button type=\"button\"class=\"close\"data-dismiss=\"modal\">&times;</button>");
            out.write("                <h4 class=\"modal-title\">Заказ успешно выполнен.</h4>");
            out.write("            </div>");
            out.write("            <div class=\"modal-body\">");
            out.write("                <p>Наш водитель приедет за вами в ближайшее время.</p>");
            out.write("            </div>");
            out.write("            <div class=\"modal-footer\">");
            out.write("                <button type=\"button\"class=\"btn btn-default\"data-dismiss=\"modal\">Закрыть</button>");
            out.write("            </div>");
            out.write("        </div>");
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
}
