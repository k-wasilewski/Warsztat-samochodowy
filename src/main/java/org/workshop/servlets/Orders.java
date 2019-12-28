package org.workshop.servlets;

import org.workshop.dao.OrderDao;
import org.workshop.models.Order;
import org.workshop.models.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/orders")
public class Orders extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id")==null) {
            OrderDao odao = new OrderDao();
            Order newOrder = new Order();
            if (!request.getParameter("planStartDate").equals("")) newOrder.setPlan_start(Date.valueOf(request.getParameter("planStartDate")));
            if (!request.getParameter("startDate").equals("")) newOrder.setActual_start(Date.valueOf(request.getParameter("startDate")));
            if (!request.getParameter("end").equals("")) newOrder.setEnd(Date.valueOf(request.getParameter("end")));
            if (!request.getParameter("employee").equals("")) newOrder.setEmployee_id(Integer.parseInt(request.getParameter("employee")));
            newOrder.setDescription_problem(request.getParameter("descriptionProblem"));
            newOrder.setDescription_repair(request.getParameter("descriptionRepair"));
            if (!request.getParameter("vehicle").equals("")) newOrder.setVehicle_id(Integer.parseInt(request.getParameter("vehicle")));
            if (!request.getParameter("priceCustomer").equals("")) newOrder.setPrice_customer(Integer.parseInt(request.getParameter("priceCustomer")));
            if (!request.getParameter("costParts").equals("")) newOrder.setCost_parts(Integer.parseInt(request.getParameter("costParts")));
            if (!request.getParameter("h").equals("")) newOrder.setH(Integer.parseInt(request.getParameter("h")));
            odao.create(newOrder);

            Order[] orders = odao.findAll();
            request.setAttribute("orders", orders);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            OrderDao odao = new OrderDao();
            Order editedOrder = odao.read(id);
            if (!request.getParameter("planStartDate").equals("")) editedOrder.setPlan_start(Date.valueOf(request.getParameter("planStartDate")));
            if (!request.getParameter("startDate").equals(""))editedOrder.setActual_start(Date.valueOf(request.getParameter("startDate")));
            if (!request.getParameter("end").equals("")) editedOrder.setEnd(Date.valueOf(request.getParameter("end")));
            if (!request.getParameter("employee").equals(""))editedOrder.setEmployee_id(Integer.parseInt(request.getParameter("employee")));
            editedOrder.setDescription_problem(request.getParameter("descriptionProblem"));
            editedOrder.setDescription_repair(request.getParameter("descriptionRepair"));
            if (!request.getParameter("vehicle").equals(""))editedOrder.setVehicle_id(Integer.parseInt(request.getParameter("vehicle")));
            if (!request.getParameter("priceCustomer").equals(""))editedOrder.setPrice_customer(Integer.parseInt(request.getParameter("priceCustomer")));
            if (!request.getParameter("costParts").equals(""))editedOrder.setCost_parts(Integer.parseInt(request.getParameter("costParts")));
            if (!request.getParameter("h").equals(""))editedOrder.setH(Integer.parseInt(request.getParameter("h")));
            odao.update(editedOrder);

            Order[] orders = odao.findAll();
            request.setAttribute("orders", orders);
        }
        getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao odao = new OrderDao();
        Order[] orders = odao.findAllDesc();
        request.setAttribute("orders", orders);
        getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
    }
}
