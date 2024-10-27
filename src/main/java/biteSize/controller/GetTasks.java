package biteSize.controller;

import biteSize.entity.Task;
import biteSize.entity.User;
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

        HttpSession session = req.getSession();
        UserController userControl = new UserController();
        String userEmail = (String)session.getAttribute("userEmail");

        User user = userControl.getUserFromEmail(userEmail);
        List<Task> taskList = user.getTasks();

        req.setAttribute("tasks", taskList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/tasks.jsp");
        dispatcher.forward(req, resp);

    }

    //TODO These are duplicate. Can be cleaned up when I have more time :)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserController userControl = new UserController();
        String userEmail = (String) session.getAttribute("userEmail");

        User user = userControl.getUserFromEmail(userEmail);
        List<Task> taskList = user.getTasks();

        req.setAttribute("tasks", taskList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/tasks.jsp");
        dispatcher.forward(req, resp);

    }

}
