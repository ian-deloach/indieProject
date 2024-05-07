package biteSize.controller;

import biteSize.entity.Schedule;
import biteSize.entity.Task;
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

        GenericDao<Schedule> scheduleDao = new GenericDao(Schedule.class);
        List<Schedule> scheduleList = scheduleDao.getAll();

        req.setAttribute("schedules", scheduleList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/schedules.jsp");
        dispatcher.forward(req, resp);

    }
}
