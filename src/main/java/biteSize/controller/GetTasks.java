package biteSize.controller;

import biteSize.entity.Task;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(
        name = "gettasks",
        urlPatterns = {"/tasks"}
)

public class GetTasks extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        GenericDao taskDao = new GenericDao(Task.class);

        req.setAttribute("tasks", taskDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/tasks.jsp");
        dispatcher.forward(req, res);

    }
}
