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
import java.util.*;

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

        UserController userControl = new UserController();
        HttpSession session = req.getSession();
        String userEmail = (String)session.getAttribute("userEmail");
        User user = userControl.getUserFromEmail(userEmail);
        List<Theme> themes = user.getThemes();

        req.setAttribute("userThemes", themes);

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

        HttpSession session = req.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        UserController control = new UserController();
        User user = control.getUserFromId(userId);
        List<Theme> userThemes = user.getThemes();

        Theme themeToBeEntered = new Theme();
        Set<String> themeNames = new HashSet<>();
        for(Theme t : userThemes) {
            themeNames.add(t.getName());
        }

        String name = req.getParameter("taskName");
        String enteredThemeName = req.getParameter("theme");
        String urgency = req.getParameter("urgent");
        String description = req.getParameter("description");
        String dispatcherUrl = "/tasks";
        session.removeAttribute("addMessage");

        // If the urgency checkbox was clicked, this sets the task as urgent
        if (Objects.equals(urgency, "on")) {
            urgency = "Urgent";
        }

        // If a task is left blank, it will be called "New Task"
        if (Objects.equals(name, "")) {
            name = "New Task";
        }

        if (themeNames.contains(enteredThemeName)) {
            GenericDao<Theme> themeDao = new GenericDao<>(Theme.class);
            themeToBeEntered = (Theme)themeDao.getPropertyEqual("name", enteredThemeName).get(0);
        }

        if (!themeNames.contains(enteredThemeName)) {
            GenericDao<Theme> themeDao = new GenericDao<>(Theme.class);
            themeToBeEntered = new Theme(user, enteredThemeName);
            themeDao.insert(themeToBeEntered);
        }


        Task newTask = new Task();
        newTask.setUser(user);
        newTask.setName(name);
        newTask.setUrgency(urgency);
        newTask.setDescription(description);
        newTask.setTheme(themeToBeEntered);

        GenericDao taskDao = new GenericDao(Task.class);
        taskDao.insert(newTask);

        // TODO Currently, this message doesn't delete as intended
        RequestDispatcher dispatcher = req.getRequestDispatcher(dispatcherUrl);
        session.setAttribute("addMessage", "Added " + name);
        dispatcher.forward(req, resp);

    }
}
