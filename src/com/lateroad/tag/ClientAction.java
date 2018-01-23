package com.lateroad.tag;

import com.lateroad.buber.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ClientAction extends TagSupport {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    @Override
//    public int doStartTag() throws JspException {
//        JspWriter out = pageContext.getOut();
//
//    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

}
