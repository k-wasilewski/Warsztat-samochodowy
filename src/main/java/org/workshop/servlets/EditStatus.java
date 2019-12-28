package org.workshop.servlets;

import org.workshop.dao.OrderDao;
import org.workshop.dao.StatusDao;
import org.workshop.models.Order;
import org.workshop.models.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editstatus")
public class EditStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        OrderDao odao = new OrderDao();
        Order editedOrder = odao.read(orderId);

        StatusDao sdao = new StatusDao();
        Status editedStatus = sdao.read(editedOrder.getStatus_id());
        Status[] statuses = sdao.findAll();

        request.setAttribute("editedStatus", editedStatus);
        request.setAttribute("statuses", statuses);
        getServletContext().getRequestDispatcher("/editstatus.jsp").forward(request, response);
    }
}
