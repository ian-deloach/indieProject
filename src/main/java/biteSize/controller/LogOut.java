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

    private String domain;
    private String clientId;

    @Override
    public void init(ServletConfig config) {
        domain = config.getServletContext().getInitParameter("com.auth0.domain");
        clientId = config.getServletContext().getInitParameter("com.auth0.clientId");
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        String returnUrl = String.format("%s://%s", request.getScheme(), request.getServerName());
        if ((request.getScheme().equals("http") && request.getServerPort() != 80) || (request.getScheme().equals("https") && request.getServerPort() != 443)) {
            returnUrl += ":" + request.getServerPort();
        }
        returnUrl += "/login";
        String logoutUrl = String.format(
                "https://%s/v2/logout?client_id=%s&returnTo=%s",
                domain,
                clientId,
                returnUrl
        );
        response.sendRedirect(logoutUrl);
    }

//    //TODO Add a way to get confirmation the user wants to log out
//
//    /**
//     * Sets the amazon cognito attributes to null
//     * @param req the request
//     * @param resp the response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        HttpSession session = req.getSession();
//        session.setAttribute("userName", null);
//        session.setAttribute("userEmail", null);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
//        dispatcher.forward(req, resp);
//    }
}
