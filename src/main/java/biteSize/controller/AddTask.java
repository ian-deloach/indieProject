package biteSize.controller;

import biteSize.entity.Task;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@WebServlet(
        name = "addTask",
        urlPatterns = {"/add-task"}
)

public class AddTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addTask.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("taskName");
        String deadlineString = req.getParameter("deadline");
        String urgency = req.getParameter("urgent");
        String description = req.getParameter("description");
        String redirectUrl = "/addTasks.jsp";
        String dispatcherUrl = "/tasks.jsp";
        HttpSession session = req.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM, d", Locale.ENGLISH);
        Date deadline = (Date) formatter.parse(deadlineString);

        if (Objects.equals(name, "")) {
            session.setAttribute("addMessage", "Please fill out the task name");
            resp.sendRedirect(redirectUrl);
        }

        GenericDao userDao = new GenericDao(User.class);
        User user = (User) userDao.getById(userId);

        Task newTask = new Task();
        newTask.setName(name);
        newTask.setDeadline(deadline);
        newTask.setUrgency(urgency);
        newTask.setDescription(description);
        newTask.setUser(user);

        GenericDao taskDao = new GenericDao(Task.class);
        taskDao.insert(newTask);

        RequestDispatcher dispatcher = req.getRequestDispatcher(dispatcherUrl);
        session.setAttribute("addMessage", "Added " + name);
        dispatcher.forward(req, resp);

    }

}
