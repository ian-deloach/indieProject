package biteSize.controller;

import biteSize.entity.Task;
import biteSize.entity.Theme;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@WebServlet(
        name = "addTask",
        urlPatterns = {"/add-task"}
)

/**
 * Servlet to create new tasks
 */
public class AddTask extends HttpServlet {

    /**
     * Simply redirects to the addTask jsp
     *
     * @param req  the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addTask.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * "Validates" user input and adds the new task to the database
     *
     * @param req  the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserController control = new UserController();

        String name = req.getParameter("taskName");
//        String deadlineString = req.getParameter("deadline");
        String urgency = req.getParameter("urgent");
        String description = req.getParameter("description");
        String dispatcherUrl = "/tasks";
        HttpSession session = req.getSession();
        session.removeAttribute("addMessage");
        int userId = Integer.parseInt(session.getAttribute("userId").toString());

        // TODO All of the 'validations' are band-aid fixes. They can be improved.

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date deadline = null;
//
//        if (!Objects.equals(deadlineString, "")) {
//            try {
//                deadline = format.parse(deadlineString);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }

        if (Objects.equals(urgency, "on")) {
            urgency = "Urgent";
        }

        if (Objects.equals(name, "")) {
            name = "New Task";
        }

        User user = control.getUserFromId(userId);

        Task newTask = new Task();
        newTask.setName(name);
        //newTask.setDeadline(deadline);
        newTask.setUrgency(urgency);
        newTask.setDescription(description);
        newTask.setUser(user);

        GenericDao taskDao = new GenericDao(Task.class);
        taskDao.insert(newTask);

        // TODO Currently, this message doesn't delete as intended
        RequestDispatcher dispatcher = req.getRequestDispatcher(dispatcherUrl);
        session.setAttribute("addMessage", "Added " + name);
        dispatcher.forward(req, resp);

    }
}
