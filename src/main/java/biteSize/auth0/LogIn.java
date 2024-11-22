package biteSize.auth0;

import biteSize.auth0.AuthenticationControllerProvider;

import biteSize.utilities.PropertiesLoader;
import com.auth0.AuthenticationController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/log-in"}
)

public class LogIn extends HttpServlet implements PropertiesLoader {

    private AuthenticationController authenticationController;
    private Properties properties;
    private String domain;

    public void init() throws ServletException {
        properties = loadProperties("/auth0.properties");
        domain = properties.getProperty("domain");
        try {
            authenticationController = AuthenticationControllerProvider.getInstance(properties);
        } catch (UnsupportedEncodingException e) {
            throw new ServletException("Couldn't create the AuthenticationController instance. Check the configuration.", e);
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        String redirectUri = req.getScheme() + "://" + req.getServerName();
        if ((req.getScheme().equals("http") && req.getServerPort() != 80) || (req.getScheme().equals("https") && req.getServerPort() != 443)) {
            redirectUri += ":" + req.getServerPort() + "/BiteSize_war";
        }
        redirectUri += "/callback";

        String authorizeUrl = authenticationController.buildAuthorizeUrl(req, res, redirectUri)
                .build();
        res.sendRedirect(authorizeUrl);
    }

}
