package org.workshop.servlets;

import org.workshop.dao.OrderDao;
import org.workshop.models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/income")
public class Income extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao odao = new OrderDao();
        Order[] orders = odao.findAll();
        Date from=null;
        Date to=null;
        if (!request.getParameter("from").equals("")) from = Date.valueOf(request.getParameter("from"));
        if (!request.getParameter("to").equals("")) to = Date.valueOf(request.getParameter("to"));
        for (int i=0; i<orders.length; i++) {
            if (orders[i].getActual_start()==null||!request.getParameter("from").equals("")&&
                    from.toLocalDate().isAfter(orders[i].getActual_start().toLocalDate())) {
                break;
            }
            if (orders[i].getEnd()==null||!request.getParameter("to").equals("")&&
                    to.toLocalDate().isBefore(orders[i].getEnd().toLocalDate())) {
                break;
            }

            if (orders[i].getEnd()==null||orders[i].getEnd().toLocalDate().isAfter(LocalDate.now())) {}
            else orders[i].setIncome(orders[i].getPrice_customer());
        }
        request.setAttribute("orders", orders);
        request.setAttribute("from", from);
        request.setAttribute("to", to);

        getServletContext().getRequestDispatcher("/income.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao odao = new OrderDao();
        Order[] orders = odao.findAll();
        for (int i=0; i<orders.length; i++) {
            if (orders[i].getEnd()==null||orders[i].getEnd().toLocalDate().isAfter(LocalDate.now())) {}
            else orders[i].setIncome(orders[i].getPrice_customer());
        }
        request.setAttribute("orders", orders);

        getServletContext().getRequestDispatcher("/income.jsp").forward(request, response);
    }
}
