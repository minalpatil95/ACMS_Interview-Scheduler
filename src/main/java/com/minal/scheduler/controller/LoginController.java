package com.minal.scheduler.controller;

import com.minal.scheduler.dao.EmployeeDao;
import com.minal.scheduler.model.Employee;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao dao = new EmployeeDao();
        String em = request.getParameter("email");
        String pw = request.getParameter("psword");
        PrintWriter pwOut = response.getWriter();
        JSONObject validate = dao.validateLogin(em, pw);
        if (validate.getInt("status") == Response.Status.OK.getStatusCode()) {
            Employee employee = dao.getEmployee(em);
            HttpSession session = request.getSession();
            session.setAttribute("username", employee.getUserName());
            session.setAttribute("email", em);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else {
            pwOut.print("<p style=\"color:red\">Incorrect Username or Password!</p>");
            RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
            view.include(request, response);
        }

        pwOut.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
        rd.forward(request, response);
    }
}
