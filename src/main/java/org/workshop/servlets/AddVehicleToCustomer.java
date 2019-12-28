package org.workshop.servlets;

import org.workshop.dao.CustomerDao;
import org.workshop.models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addvehicletocustomer")
public class AddVehicleToCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        CustomerDao cdao = new CustomerDao();
        Customer customer = cdao.read(customerId);
        request.setAttribute("customer", customer);

        getServletContext().getRequestDispatcher("/addvehicletocustomer.jsp").forward(request, response);
    }
}
