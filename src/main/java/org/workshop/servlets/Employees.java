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
import java.sql.Date;

@WebServlet("/employees")
public class Employees extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id").equals("")) {
            EmployeeDao edao = new EmployeeDao();
            Employee newEmployee = new Employee();
            newEmployee.setFirst_name(request.getParameter("firstName"));
            newEmployee.setLast_name(request.getParameter("lastName"));
            newEmployee.setAddress(request.getParameter("address"));
            if (!request.getParameter("phone").equals("")) newEmployee.setPhone(Integer.parseInt(request.getParameter("phone")));
            newEmployee.setNote(request.getParameter("note"));
            if (!request.getParameter("pay_h").equals("")) newEmployee.setPay_h(Integer.parseInt(request.getParameter("pay_h")));
            edao.create(newEmployee);

            Employee[] employees = edao.findAll();
            request.setAttribute("employees", employees);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            EmployeeDao edao = new EmployeeDao();
            Employee editedEmployee= edao.read(id);
            editedEmployee.setFirst_name(request.getParameter("firstName"));
            editedEmployee.setLast_name(request.getParameter("lastName"));
            editedEmployee.setAddress(request.getParameter("address"));
            if (!request.getParameter("phone").equals("")) editedEmployee.setPhone(Integer.parseInt(request.getParameter("phone")));
            editedEmployee.setNote(request.getParameter("note"));
            if (!request.getParameter("pay_h").equals("")) editedEmployee.setPay_h(Integer.parseInt(request.getParameter("pay_h")));
            edao.update(editedEmployee);

            Employee[] employees = edao.findAll();
            request.setAttribute("employees", employees);
        }
        getServletContext().getRequestDispatcher("/employees.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao edao = new EmployeeDao();
        Employee[] employees = edao.findAll();
        request.setAttribute("employees", employees);
        getServletContext().getRequestDispatcher("/employees.jsp").forward(request, response);
    }
}
