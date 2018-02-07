package com.lateroad.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SuccessOrderModal extends TagSupport {


    @Override
    public int doStartTag() {
        String language = (String) this.pageContext.getSession().getAttribute("language");
        ResourceBundle bundle = ResourceBundle.getBundle("translation", new Locale(language));

        JspWriter out = pageContext.getOut();
        try {
            out.write("<!--Modal -->");
            out.write("<div id=\"successOrder\"class=\"modal fade\"role=\"dialog\">");
            out.write("    <div class=\"modal-dialog\">");
            out.write("    <!--Modal content-->");
            out.write("        <div class=\"modal-content\">");
            out.write("            <div class=\"modal-header\">");
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
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
