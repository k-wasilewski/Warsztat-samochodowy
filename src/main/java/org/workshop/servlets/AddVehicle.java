package org.workshop.servlets;

import org.workshop.dao.CustomerDao;
import org.workshop.models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addvehicle")
public class AddVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDao cdao = new CustomerDao();
        Customer[] customers = cdao.findAll();

        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/addvehicle.jsp").forward(request, response);
    }
}
