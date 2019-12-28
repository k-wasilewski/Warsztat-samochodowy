package org.workshop.servlets;

import org.workshop.dao.EmployeeDao;
import org.workshop.dao.OrderDao;
import org.workshop.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orderdetails")
public class OrderDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        OrderDao odao = new OrderDao();
        Order order = odao.read(orderId);

        EmployeeDao edao = new EmployeeDao();


        request.setAttribute("order", order);
        getServletContext().getRequestDispatcher("/orderdetails.jsp").forward(request, response);
    }
}
