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
import java.util.Arrays;

@WebServlet("/addorder")
public class AddOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao edao = new EmployeeDao();
        Employee[] employees = edao.findAll();
        request.setAttribute("employees", employees);

        StatusDao sdao = new StatusDao();
        Status[] statuses=sdao.findAll();
        request.setAttribute("statuses", statuses);

        VehicleDao vdao = new VehicleDao();
        Vehicle[] vehicles = vdao.findAll();
        request.setAttribute("vehicles", vehicles);

        getServletContext().getRequestDispatcher("/addorder.jsp").forward(request, response);
    }
}
