package biteSize.auth0;

import biteSize.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.HTTP;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Properties;

/**
 * A servlet to log users out
 */

@WebServlet("/log-out")
public class LogOut extends HttpServlet implements PropertiesLoader {

    private String domain;
    private String clientId;

    @Override
    public void init() {
        Properties properties = loadProperties("/auth0.properties");
        domain = properties.getProperty("domain");
        clientId = properties.getProperty("client.id");
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        String returnUrl = String.format("%s://%s", request.getScheme(), request.getServerName());
        if ((request.getScheme().equals("http") && request.getServerPort() != 80) || (request.getScheme().equals("https") && request.getServerPort() != 443)) {
            returnUrl += ":" + request.getServerPort() + "/BiteSize_war";
        }
        String logoutUrl = String.format(
                "https://%s/v2/logout?client_id=%s&returnTo=%s",
                domain,
                clientId,
                returnUrl
        );
        response.sendRedirect(logoutUrl);
    }
}
