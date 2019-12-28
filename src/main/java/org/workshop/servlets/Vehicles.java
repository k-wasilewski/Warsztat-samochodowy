package org.workshop.servlets;

import org.workshop.dao.EmployeeDao;
import org.workshop.dao.VehicleDao;
import org.workshop.models.Employee;
import org.workshop.models.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/vehicles")
public class Vehicles extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id")==null||request.getParameter("id").equals("")) {
            VehicleDao vdao = new VehicleDao();
            Vehicle newVehicle = new Vehicle();
            newVehicle.setModel(request.getParameter("model"));
            newVehicle.setMake(request.getParameter("make"));
            if (!request.getParameter("dateOfProduction").equals("")) newVehicle.setDop(Date.valueOf(request.getParameter("dateOfProduction")));
            newVehicle.setLic(request.getParameter("license"));
            if (!request.getParameter("nextService").equals("")) newVehicle.setNext_service(Date.valueOf(request.getParameter("nextService")));
            newVehicle.setCustomer_id(Integer.parseInt(request.getParameter("customer")));

            vdao.create(newVehicle);

            Vehicle[] vehicles = vdao.findAll();
            request.setAttribute("vehicles", vehicles);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            VehicleDao vdao = new VehicleDao();
            Vehicle editedVehicle= vdao.read(id);
            editedVehicle.setModel(request.getParameter("model"));
            editedVehicle.setMake(request.getParameter("make"));
            if (!request.getParameter("dateOfProduction").equals("")) editedVehicle.setDop(Date.valueOf(request.getParameter("dateOfProduction")));
            editedVehicle.setLic(request.getParameter("license"));
            if (!request.getParameter("nextService").equals("")) editedVehicle.setNext_service(Date.valueOf(request.getParameter("nextService")));
            editedVehicle.setCustomer_id(Integer.parseInt(request.getParameter("customer")));
            vdao.update(editedVehicle);

            Vehicle[] vehicles = vdao.findAll();
            request.setAttribute("vehicles", vehicles);
        }
        getServletContext().getRequestDispatcher("/vehicles.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VehicleDao vdao = new VehicleDao();
        Vehicle[] vehicles = vdao.findAll();
        request.setAttribute("vehicles", vehicles);
        getServletContext().getRequestDispatcher("/vehicles.jsp").forward(request, response);
    }
}
