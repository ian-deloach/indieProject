package biteSize.controller;

import org.json.HTTP;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * A servlet to log users out
 */

@WebServlet("/log-out")
public class LogOut extends HttpServlet {
    //TODO Add a way to get confirmation the user wants to log out

    /**
     * Sets the amazon cognito attributes to null
     * @param req the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("userName", null);
        session.setAttribute("userEmail", null);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
