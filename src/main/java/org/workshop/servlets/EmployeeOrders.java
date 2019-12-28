package org.workshop.servlets;

import org.workshop.dao.EmployeeDao;
import org.workshop.dao.OrderDao;
import org.workshop.models.Employee;
import org.workshop.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employeeorders")
public class EmployeeOrders extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        OrderDao odao = new OrderDao();
        Order[] orders = odao.findAllByEmployee(employeeId);
        EmployeeDao edao = new EmployeeDao();
        Employee employee = edao.read(employeeId);

        request.setAttribute("employee", employee);
        request.setAttribute("orders", orders);
        getServletContext().getRequestDispatcher("/employeeorders.jsp").forward(request, response);
    }
}
