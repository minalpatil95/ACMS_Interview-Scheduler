package com.minal.scheduler.controller;

import com.minal.scheduler.dao.EventDao;
import com.minal.scheduler.model.Employee;
import com.minal.scheduler.model.Event;
import com.minal.scheduler.model.Round;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "SelectInterviewersController")
public class SelectInterviewersController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int eventId = Integer.parseInt(request.getParameter("id"));
        final Event event = Event.find(eventId);
        Boolean check = EventDao.findEvent(eventId);
        if(check){
            request.setAttribute("check",check);
        }
        Collection<Round> rnd = Event.getRoundsOfEvent(event.getId());
        event.setRounds(rnd);
        Collection<Employee> i = Employee.getAllInterviewers();
        request.setAttribute("interviewers",i);
        request.setAttribute("event",event);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/selectInterviewers.jsp");
        rd.forward(request, response);
    }
}
