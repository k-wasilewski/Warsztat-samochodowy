package org.workshop.servlets;

import org.workshop.dao.EmployeeDao;
import org.workshop.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delemployee")
public class DelEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        EmployeeDao edao = new EmployeeDao();
        OrderDao odao = new OrderDao();
        if (odao.findAllByEmployee(employeeId).length==0) {
            edao.delete(employeeId);
        } else {
            request.setAttribute("msg", "Nie można usunąć pracownika, który ma przypisane zlecenia");
        }
        getServletContext().getRequestDispatcher("/employees").forward(request, response);
    }
}
