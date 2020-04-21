package com.minal.scheduler.controller;

import com.minal.scheduler.dao.EventDao;
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
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "HomepageRecruiterController")
public class HomepageRecruiterController extends HttpServlet {
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
            Collection<Event> events = Event.getAllRecruitersEvent(employee.getId());
            ArrayList<Boolean> check = new ArrayList<Boolean>();
            for(Event e: events) {
                Boolean b = EventDao.findEvent(e.getId());
                check.add(b);
            }
            request.setAttribute("check",check);
            request.setAttribute("employee", employee);
            request.setAttribute("events",events);
            //System.out.println(events);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/homepageRecruiter.jsp");
            rd.forward(request,response);
        }
    }
}
