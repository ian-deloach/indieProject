package biteSize.controller;

import biteSize.entity.Task;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "getTasks",
        urlPatterns = {"/tasks"}
)

public class GetTasks extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao taskDao = new GenericDao(Task.class);
        HttpSession session = req.getSession();
        String userEmail = (String)session.getAttribute("userEmail");
        List<Task> taskList = taskDao.getAll();

        req.setAttribute("tasks", taskList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/tasks.jsp");
        dispatcher.forward(req, resp);

    }
}
