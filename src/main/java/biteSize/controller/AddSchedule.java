package biteSize.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "addSchedule",
        urlPatterns = {"/add-schedule"}
)


/**
 * Servlet used to create a new schedule
 */
public class AddSchedule extends HttpServlet {

    /**
     * Redirects to the schedules jsp
     * @param req the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/schedules.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Takes the schedule name input and calls the method
     * to create a randomized schedule to add to the user's database
     *
     * @param req the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
        TaskController taskControl = new TaskController();

        String scheduleName = req.getParameter("scheduleName");
        taskControl.generateSchedule(scheduleName, userId);

        // TODO figure out why this wont redirect properly
        RequestDispatcher dispatcher = req.getRequestDispatcher("/schedules");
        dispatcher.forward(req, resp);

    }

}
