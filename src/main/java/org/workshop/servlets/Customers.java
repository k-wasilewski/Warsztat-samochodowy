package org.workshop.servlets;

import org.workshop.dao.CustomerDao;
import org.workshop.dao.OrderDao;
import org.workshop.models.Customer;
import org.workshop.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/customers")
public class Customers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id")==null) {
            CustomerDao cdao = new CustomerDao();
            Customer newCustomer = new Customer();
            newCustomer.setFirst_name(request.getParameter("firstName"));
            newCustomer.setLast_name(request.getParameter("lastName"));
            if (request.getParameter("dateOfBirth")!=null) newCustomer.setDob(Date.valueOf(request.getParameter("dateOfBirth")));
            cdao.create(newCustomer);

            Customer[] customers = cdao.findAll();
            request.setAttribute("customers", customers);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            CustomerDao cdao = new CustomerDao();
            Customer editedCustomer = cdao.read(id);
            editedCustomer.setFirst_name(request.getParameter("firstName"));
            editedCustomer.setLast_name(request.getParameter("lastName"));
            if (request.getParameter("dateOfBirth")!=null) editedCustomer.setDob(Date.valueOf(request.getParameter("dateOfBirth")));
            cdao.update(editedCustomer);

            Customer[] customers = cdao.findAll();
            request.setAttribute("customers", customers);
        }
        if (request.getParameter("search")!=null) {
            CustomerDao cdao = new CustomerDao();
            Customer[] customersSearched = cdao.findCustomersByName(request.getParameter("search"));
            request.setAttribute("customersSearched", customersSearched);

            getServletContext().getRequestDispatcher("/customerssearched.jsp").forward(request, response);
        }
        else getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDao cdao = new CustomerDao();
        Customer[] customers = cdao.findAll();
        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);
    }
}
