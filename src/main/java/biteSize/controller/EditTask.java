package biteSize.controller;

import biteSize.entity.Task;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

        // TODO This is VERY similar to add task. Create a method to validate or add hibernate validation
        int id = Integer.parseInt(req.getParameter("taskId"));
        String name = req.getParameter("taskName");
        String deadlineString = req.getParameter("deadline");
        String urgency = req.getParameter("urgent");
        String description = req.getParameter("description");
        String dispatcherUrl = "/tasks";
        HttpSession session = req.getSession();
        session.removeAttribute("addMessage");
        int userId = Integer.parseInt(session.getAttribute("userId").toString());

        // TODO Incorporate deadline at some point, like add task
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

        Task taskToUpdate = (Task)taskDao.getById(id);
        taskToUpdate.setName(name);
        taskToUpdate.setUrgency(urgency);
        taskToUpdate.setDescription(description);
        taskDao.update(taskToUpdate);

        // TODO Find a way to get the confirmation messages to work properly
        RequestDispatcher dispatcher = req.getRequestDispatcher(dispatcherUrl);
        dispatcher.forward(req, resp);

    }
}
