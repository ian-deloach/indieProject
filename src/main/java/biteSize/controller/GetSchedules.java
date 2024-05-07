package biteSize.controller;

import biteSize.entity.Schedule;
import biteSize.entity.Task;
import biteSize.entity.User;
import biteSize.persistence.GenericDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "getSchedules",
        urlPatterns = {"/schedules"}
)

public class GetSchedules extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        GenericDao userDao = new GenericDao(User.class);
        String userEmail = (String)session.getAttribute("userEmail");

        List<User> foundUsers = userDao.getPropertyEqual("email", userEmail);
        User user = foundUsers.get(0);
        List<Schedule> scheduleList = user.getSchedules();

        req.setAttribute("schedules", scheduleList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/schedules.jsp");
        dispatcher.forward(req, resp);

    }
}
