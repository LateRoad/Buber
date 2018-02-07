package com.lateroad.buber.listener;

import com.lateroad.buber.command.impl.SignOutCommand;
import com.lateroad.buber.model.CurrentModel;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger logger = Logger.getLogger(SessionListener.class);

    // Public constructor is required by servlet spec
    public SessionListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        logger.info("Session created. " + se.getSession().getMaxInactiveInterval());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        CurrentModel currentUser = (CurrentModel) se.getSession().getAttribute("user");
        logger.info("Session destroyed." + se.getSession().getLastAccessedTime() + " " + currentUser.getCurrentUser().getLogin());
        SignOutCommand command = new SignOutCommand();
        command.execute(se.getSession());
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}