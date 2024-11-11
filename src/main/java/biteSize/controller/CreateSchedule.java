package biteSize.controller;

import biteSize.entity.Task;
import biteSize.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "createSchedule",
        urlPatterns = {"/create-schedule"}
)

public class CreateSchedule extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserController userControl = new UserController();
        String userEmail = (String)session.getAttribute("userEmail");

        User user = userControl.getUserFromEmail(userEmail);
        List<Task> taskList = user.getTasks();

        req.setAttribute("tasks", taskList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createSchedule.jsp");
        dispatcher.forward(req, resp);

    }

}
