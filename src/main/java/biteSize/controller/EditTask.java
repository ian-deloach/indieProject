package biteSize.controller;

import biteSize.entity.Task;
import biteSize.entity.Theme;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Servlet to send task information to an edit form
 */

@WebServlet(
        name = "editTask",
        urlPatterns = {"/edit-task"}
)

public class EditTask extends HttpServlet {

    /**
     * Gets an id parameter and sends the associated task information to the edit task form.
     * @param req the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao taskDao = new GenericDao(Task.class);

        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("taskId", id);
        String url = "/editTask.jsp";

        Task task = (Task)taskDao.getById(id);
        req.setAttribute("task", task);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);

    }

    /**
     * "Validates" user input and updates the task that shares the id
     * @param req the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("taskId"));
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
        String urgency = req.getParameter("urgent");
        String description = req.getParameter("description");
        String enteredThemeName = req.getParameter("theme");
        String dispatcherUrl = "/tasks";
        session.removeAttribute("addMessage");

        if (Objects.equals(urgency, "on")) {
            urgency = "Urgent";
        }

        GenericDao taskDao = new GenericDao(Task.class);

        // Sets the name back to the old name if the edit field is left blank.
        if (Objects.equals(name, "")) {
            Task task = (Task)taskDao.getById(id);
            name = task.getName();
        }

        if (Objects.equals(urgency, "on")) {
            urgency = "Urgent";
        }

        // If a theme exists with the same name as the entered name, use that instead
        if (themeNames.contains(enteredThemeName)) {
            GenericDao<Theme> themeDao = new GenericDao<>(Theme.class);
            themeToBeEntered = (Theme)themeDao.getPropertyEqual("name", enteredThemeName).get(0);
        }

        // Otherwise, create a new theme object and insert that.
        if (!themeNames.contains(enteredThemeName)) {
            GenericDao<Theme> themeDao = new GenericDao<>(Theme.class);
            themeToBeEntered = new Theme(user, enteredThemeName);
            themeDao.insert(themeToBeEntered);
        }

        Task taskToUpdate = (Task)taskDao.getById(id);
        taskToUpdate.setName(name);
        taskToUpdate.setUrgency(urgency);
        taskToUpdate.setTheme(themeToBeEntered);
        taskToUpdate.setDescription(description);
        taskDao.update(taskToUpdate);

        // TODO Find a way to get the confirmation messages to work properly
        RequestDispatcher dispatcher = req.getRequestDispatcher(dispatcherUrl);
        dispatcher.forward(req, resp);

    }
}
