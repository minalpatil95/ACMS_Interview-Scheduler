package com.minal.scheduler.controller;

import com.minal.scheduler.model.Employee;
import com.minal.scheduler.model.Event;
import com.minal.scheduler.model.Round;
import com.minal.scheduler.utils.TokenAuth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "EventDetailsForInterviewerController")
public class EventDetailsForInterviewerController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Employee employee = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("authToken")){
                    employee = TokenAuth.getEmployeeFromToken(cookie.getValue());
                }
            }
        }
        if( employee == null){
            RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
            rd.forward(request,response );
        }
        else {
            final int eventId = Integer.parseInt(request.getParameter("id"));
            final Event event = Event.find(eventId);
            Collection<Round> rnd = Event.getRoundsOfEvent(event.getId());
            event.setRounds(rnd);
            Collection<Event> pendingEvents = Event.getAllInterviewersPendingEvent(employee.getId());
            request.setAttribute("pendingEvents",pendingEvents);
            request.setAttribute("event", event);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/eventDetailsForInterviewer.jsp");
            rd.forward(request, response);
        }
    }
}
