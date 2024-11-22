package biteSize.auth0;

import biteSize.utilities.PropertiesLoader;
import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@WebServlet(urlPatterns = {"/callback"})

public class CallbackServlet extends HttpServlet implements PropertiesLoader {

    private String redirectOnSuccess;
    private String redirectOnFail;
    private Properties properties;
    private AuthenticationController authenticationController;

    public void init() throws ServletException {
        properties = loadProperties("/auth0.properties");
        redirectOnSuccess = "http://localhost:8080/BiteSize_war/";
        redirectOnFail = "http://localhost:8080/BiteSize_war/";
        try {
            authenticationController = AuthenticationControllerProvider.getInstance(properties);
        } catch (UnsupportedEncodingException e) {
            throw new ServletException("Couldn't create the AuthenticationController instance. Check the configuration.", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            // Parse the request
            Tokens tokens = authenticationController.handle(req, res);
            SessionUtils.set(req, "accessToken", tokens.getAccessToken());
            SessionUtils.set(req, "idToken", tokens.getIdToken());
            res.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }
}
