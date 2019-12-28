package org.workshop.servlets;

import org.workshop.dao.EmployeeDao;
import org.workshop.dao.StatusDao;
import org.workshop.dao.VehicleDao;
import org.workshop.models.Employee;
import org.workshop.models.Status;
import org.workshop.models.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addordertoemployee")
public class AddOrderToEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        EmployeeDao edao = new EmployeeDao();
        Employee employee = edao.read(employeeId);

        Employee[] employees = edao.findAll();
        request.setAttribute("employees", employees);

        StatusDao sdao = new StatusDao();
        Status[] statuses=sdao.findAll();
        request.setAttribute("statuses", statuses);

        VehicleDao vdao = new VehicleDao();
        Vehicle[] vehicles = vdao.findAll();
        request.setAttribute("vehicles", vehicles);

        request.setAttribute("employee", employee);
        getServletContext().getRequestDispatcher("/addordertoemployee.jsp").forward(request, response);
    }
}
