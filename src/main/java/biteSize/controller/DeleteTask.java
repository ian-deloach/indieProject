package biteSize.controller;

import biteSize.entity.Task;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet for deleting tasks
 */

@WebServlet (
        name = "deleteTask",
        urlPatterns = "/delete-task"
)

public class DeleteTask extends HttpServlet {

    /**
     * Deletes the selected task from within the edit task page
     * @param req the request
     * @param resp the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao taskDao = new GenericDao(Task.class);

        int id = Integer.parseInt(req.getParameter("id"));
        String url = "/tasks";
        Task taskToDelete = (Task)taskDao.getById(id);
        taskDao.delete(taskToDelete);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);

    }
}
