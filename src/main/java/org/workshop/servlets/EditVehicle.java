package org.workshop.servlets;

import org.workshop.dao.*;
import org.workshop.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editvehicle")
public class EditVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("id"));
        VehicleDao vdao = new VehicleDao();
        Vehicle editedVehicle = vdao.read(vehicleId);

        CustomerDao cdao = new CustomerDao();
        Customer[] customers = cdao.findAll();

        request.setAttribute("editedVehicle", editedVehicle);
        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/editvehicle.jsp").forward(request, response);
    }
}
