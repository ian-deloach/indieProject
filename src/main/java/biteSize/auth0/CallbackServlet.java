package biteSize.auth0;

import biteSize.utilities.PropertiesLoader;
import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.io.*;
import java.net.*;

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

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            // Parse the request
            Tokens tokens = authenticationController.handle(req, res);
            SessionUtils.set(req, "accessToken", tokens.getAccessToken());
            SessionUtils.set(req, "idToken", tokens.getIdToken());
            SessionUtils.set(req, "username", getUsername(tokens.getIdToken()));
            res.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }

    public String getUsername(String idToken) throws IOException {
        String username = "User";
        //TODO MAKE USER ID WORK WITH UNIREST SO IT DOESNT THROW ERROR WHEN USING SYMBOL
        HttpResponse<String> response = Unirest.get("https://" + properties.getProperty("domain")
                        + "/api/v2/users/" + "auth0|673faca2982953eb4dd2c441")
                .header("authorization", "Bearer " + properties.getProperty("bearer"))
                .asString();

        System.out.println(response);
        return username;
    }
}
