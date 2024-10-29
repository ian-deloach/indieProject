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

//// TODO THIS CODE DOES NOT WORK UNTIL AWS IS REINSTATED INTO PROJECT
//
//    /**
//     * Begins the authentication process using AWS Cognito
//     */
//    public class LogIn extends HttpServlet implements PropertiesLoader {
//        Properties properties;
//        private final Logger logger = LogManager.getLogger(this.getClass());
//        public static String CLIENT_ID;
//        public static String LOGIN_URL;
//        public static String REDIRECT_URL;
//
//        @Override
//        public void init() throws ServletException {
//            super.init();
//            loadProperties();
//        }
//
//        /**
//         * Read in the cognito props file and get the client id and required urls
//         * for authenticating a user.
//         */
//        // 4 to do this work a single time and put the properties in the application scope
//        private void loadProperties() {
//            try {
//                properties = loadProperties("/cognito.properties");
//                CLIENT_ID = properties.getProperty("client.id");
//                LOGIN_URL = properties.getProperty("loginURL");
//                REDIRECT_URL = properties.getProperty("redirectURL");
//            } catch (Exception e) {
//                logger.error("Error loading properties" + e.getMessage(), e);
//            }
//        }
//
//        /**
//         * Route to the aws-hosted cognito login page.
//         *
//         * @param req  servlet request
//         * @param resp servlet response
//         * @throws ServletException
//         * @throws IOException
//         */
//        @Override
//        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            String url = LOGIN_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
//            resp.sendRedirect(url);
//        }
//    }
