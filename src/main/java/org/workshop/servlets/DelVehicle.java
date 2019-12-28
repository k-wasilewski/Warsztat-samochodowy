package org.workshop.servlets;

import org.workshop.dao.CustomerDao;
import org.workshop.dao.OrderDao;
import org.workshop.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delvehicle")
public class DelVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("id"));
        VehicleDao vdao = new VehicleDao();
        vdao.delete(vehicleId);
        OrderDao odao = new OrderDao();
        if (odao.findAllByVehicle(vehicleId).length==0) {
            vdao.delete(vehicleId);
        } else {
            request.setAttribute("msg", "Nie można usunąć pojazdu, który ma przypisane zlecenia");
        }
        getServletContext().getRequestDispatcher("/vehicles").forward(request, response);
    }
}
