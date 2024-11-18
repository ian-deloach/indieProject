package biteSize.controller;

import biteSize.entity.User;
import biteSize.utilities.PropertiesLoader;

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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logIn.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserController userControl = new UserController();

        String userEmail = req.getParameter("email");
        String userPassword = req.getParameter("password");
        User loggedInUser = userControl.getUserFromEmail(userEmail);

        session.setAttribute("userName", loggedInUser.getName());
        session.setAttribute("userId", loggedInUser.getId());
        session.setAttribute("userEmail", loggedInUser.getEmail());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);

    }

}
