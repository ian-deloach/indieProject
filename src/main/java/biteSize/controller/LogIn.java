package biteSize.controller;

import biteSize.auth0.*;
import biteSize.entity.User;
import biteSize.utilities.PropertiesLoader;

import com.auth0.AuthenticationController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/logIn"}
)

public class LogIn extends HttpServlet {

    private AuthenticationController authenticationController;
    private String domain;
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        String redirectUri = req.getScheme() + "://" + req.getServerName();
        if ((req.getScheme().equals("http") && req.getServerPort() != 80) || (req.getScheme().equals("https") && req.getServerPort() != 443)) {
            redirectUri += ":" + req.getServerPort();
        }
        redirectUri += "/callback";

        String authorizeUrl = authenticationController.buildAuthorizeUrl(req, res, redirectUri)
                .build();
        res.sendRedirect(authorizeUrl);
    }


//    My band-aid fix method for when I don't want to deal with auth0
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logIn.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        HttpSession session = req.getSession();
//        UserController userControl = new UserController();
//
//        String userEmail = req.getParameter("email");
//        String userPassword = req.getParameter("password");
//        User loggedInUser = userControl.getUserFromEmail(userEmail);
//
//        session.setAttribute("userName", loggedInUser.getName());
//        session.setAttribute("userId", loggedInUser.getId());
//        session.setAttribute("userEmail", loggedInUser.getEmail());
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
//        dispatcher.forward(req, resp);
//
//    }

}
