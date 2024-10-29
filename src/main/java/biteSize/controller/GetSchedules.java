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

        UserController userControl = new UserController();
        HttpSession session = req.getSession();
        String userEmail = (String)session.getAttribute("userEmail");

        User user = userControl.getUserFromEmail(userEmail);
        List<Schedule> scheduleList = user.getSchedules();

        req.setAttribute("userSchedules", scheduleList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/schedules.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
