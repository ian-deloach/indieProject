package biteSize.controller;

import biteSize.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;

/**
 * An initialization process for the webapp
 * @author IanDeLoach
 */

@WebServlet (
        name = "applicationStartup",
        urlPatterns = {"/biteSize-startup"},
        loadOnStartup = 1
)

public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public void init() throws ServletException {
        Properties properties = loadProperties("database.properties");
        ServletContext servletContext = getServletContext();
    }
}