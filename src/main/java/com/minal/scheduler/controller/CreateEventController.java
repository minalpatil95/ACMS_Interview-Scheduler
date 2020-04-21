package com.minal.scheduler.controller;

import com.minal.scheduler.dao.EventDao;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "CreateEventController")
public class CreateEventController extends HttpServlet {
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        Employee employee = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("authToken")){
                    employee = TokenAuth.getEmployeeFromToken(cookie.getValue());
                }
            }
        }
        if(employee==null){
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request,response);
        }else {
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/createEvent.jsp");
            request.setAttribute("employee", employee.getId());
            rd.forward(request, response);


            PrintWriter pwOut = response.getWriter();
            Event event = new Event();
        *//*String[] date = request.getParameterValues("eventDate");
        SimpleDateFormat formatter =new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(date[0]);
        Date eventDate = null;
        try {

            eventDate =(Date) formatter.parse(date[0]);
            formatter.format(eventDate);
            //eventDate = (Date)new SimpleDateFormat("dd-MM-yyyy").parse(date[0]);
        }
        catch (ParseException p) {
            System.out.println(p);
        }
        System.out.println(formatter.format(eventDate));*//*

            String date = request.getParameter("eventDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date eventDate = null;
            try {
                eventDate = sdf.parse(date);
                String stringdate = sdf.format(eventDate);
                eventDate = sdf.parse(stringdate);
                event.setEventDate(sdf.parse(stringdate));
                System.out.println(eventDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            event.setEventName(request.getParameter("eventName"));
            event.setEventType(request.getParameter("eventType"));
            event.setNumOfRounds(Integer.parseInt(request.getParameter("round")));
            event.setJobLevel(request.getParameter("jobLevel"));
            event.setEmployee(employee);
//        event.setEventDate(eventDate);

            EventDao eventDao = new EventDao();
            //eventDao.createEvent(event);
            pwOut.print("Registration Successful! Please Login.");
            response.setContentType("text/html");
            RequestDispatcher view = request.getRequestDispatcher("jsp/createEvent.jsp");
            view.include(request, response); //index page is reloaded with text for new user to login
        }
    }
*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        Employee employee = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("authToken")){
                    employee = TokenAuth.getEmployeeFromToken(cookie.getValue());
                }
            }
        }
        if(employee==null){
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request,response);
        }else {
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/createEvent.jsp");
            request.setAttribute("employee", employee.getId());
            rd.forward(request, response);


            PrintWriter pwOut = response.getWriter();

        /*String[] date = request.getParameterValues("eventDate");
        SimpleDateFormat formatter =new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(date[0]);
        Date eventDate = null;
        try {

            eventDate =(Date) formatter.parse(date[0]);
            formatter.format(eventDate);
            //eventDate = (Date)new SimpleDateFormat("dd-MM-yyyy").parse(date[0]);
        }
        catch (ParseException p) {
            System.out.println(p);
        }
        System.out.println(formatter.format(eventDate));*/

            String date = request.getParameter("eventDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date eventDate = null;
            try {
                eventDate = sdf.parse(date);
                /*String stringdate = sdf.format(eventDate);
                eventDate = sdf.parse(stringdate);*/
                System.out.println(eventDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String ename = request.getParameter("eventName");
            String etype = request.getParameter("eventType");
            int numrounds = Integer.parseInt(request.getParameter("round"));
            String joblevel = request.getParameter("jobLevel");

            String[] comp = request.getParameterValues("comp");

            ArrayList<String> rounds = new ArrayList<String>();

            for(int i =0;i<comp.length;i++)
            {
                /* We are adding each array's element to the ArrayList*/
                rounds.add(comp[i]);
            }

            EventDao eventDao = new EventDao();
            eventDao.createEvent(ename,etype,eventDate,joblevel,numrounds,employee);
            Event e = eventDao.getEventByEventName(ename);
            eventDao.createRound(e.getId(),rounds);

            Collection<Round> rnd = Event.getRoundsOfEvent(e.getId());
            e.setRounds(rnd);
            request.setAttribute("event",e);
            RequestDispatcher r = request.getRequestDispatcher("jsp/createEvent.jsp");
            r.include(request, response); //index page is reloaded with text for new user to login
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/createEvent.jsp");
        rd.forward(request, response);
    }
}
