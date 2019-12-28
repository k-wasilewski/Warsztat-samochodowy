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
import java.util.List;

@WebServlet("")
public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao odao = new OrderDao();
        Order[] orders = odao.findAll();
        request.setAttribute("orders", orders);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
