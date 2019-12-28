package org.workshop.servlets;

import org.workshop.dao.CustomerDao;
import org.workshop.dao.EmployeeDao;
import org.workshop.dao.OrderDao;
import org.workshop.dao.VehicleDao;
import org.workshop.models.Customer;
import org.workshop.models.Employee;
import org.workshop.models.Order;
import org.workshop.models.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vehicledetails")
public class VehicleDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("id"));
        VehicleDao vdao = new VehicleDao();
        Vehicle vehicle = vdao.read(vehicleId);
        CustomerDao cdao = new CustomerDao();
        Customer owner = cdao.read(vehicle.getCustomer_id());
        OrderDao odao = new OrderDao();
        Order[] orders = odao.findAllByVehicle(vehicleId);

        request.setAttribute("vehicle", vehicle);
        request.setAttribute("orders", orders);
        request.setAttribute("owner", owner);
        getServletContext().getRequestDispatcher("/vehicledetails.jsp").forward(request, response);
    }
}
