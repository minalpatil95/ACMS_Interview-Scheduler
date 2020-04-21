package com.minal.scheduler.controller;

import com.minal.scheduler.model.Employee;
import com.minal.scheduler.model.Event;
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

@WebServlet(name = "InterviewRequestController")
public class InterviewRequestController extends HttpServlet {
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
            Collection<Event> pendingEvent = Event.getAllInterviewersPendingEvent(employee.getId());
            request.setAttribute("employee", employee);
            request.setAttribute("pendingEvents",pendingEvent);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/interviewRequest.jsp");
            rd.forward(request,response);
        }
    }
}
