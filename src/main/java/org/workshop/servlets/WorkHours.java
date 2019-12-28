package org.workshop.servlets;

import org.workshop.dao.EmployeeDao;
import org.workshop.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/workhours")
public class WorkHours extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao edao = new EmployeeDao();
        Employee[] employees = edao.findAll();
        request.setAttribute("employees", employees);
        if (request.getParameter("from")!=null) request.setAttribute("from", request.getParameter("from"));
        if (request.getParameter("to")!=null) request.setAttribute("to", request.getParameter("to"));

        getServletContext().getRequestDispatcher("/workhours.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao edao = new EmployeeDao();
        Employee[] employees = edao.findAll();
        request.setAttribute("employees", employees);

        getServletContext().getRequestDispatcher("/workhours.jsp").forward(request, response);
    }
}
