package org.workshop.servlets;

import org.workshop.dao.CustomerDao;
import org.workshop.dao.OrderDao;
import org.workshop.dao.VehicleDao;
import org.workshop.models.Customer;
import org.workshop.models.Order;
import org.workshop.models.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customerdetails")
public class CustomerDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        CustomerDao cdao = new CustomerDao();
        Customer customer = cdao.read(customerId);

        VehicleDao vdao = new VehicleDao();
        Vehicle[] customersVehicles = vdao.findAllByCustomer(customerId);

        request.setAttribute("customersVehicles", customersVehicles);
        request.setAttribute("customer", customer);
        getServletContext().getRequestDispatcher("/customerdetails.jsp").forward(request, response);
    }
}
