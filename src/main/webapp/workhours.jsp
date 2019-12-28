<%@ page import="org.workshop.dao.StatusDao" %>
<%@ page import="org.workshop.models.Status" %>
<%@ page import="org.workshop.models.Order" %>
<%@ page import="org.workshop.models.Employee" %>
<%@ page import="org.workshop.dao.EmployeeDao" %>
<%@ page import="org.workshop.dao.VehicleDao" %>
<%@ page import="org.workshop.models.Vehicle" %>
<%@ page import="org.workshop.dao.OrderDao" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Warsztat samochodowy</title>



    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

</head>
<body>
<%@ include file="header.jsp" %>

<section>
    <br><br><br>
    <div class="row">
        <div class="col-md-4">
            <h4>Raport przepracowanych godzin</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/';">
                Powrót
            </button>
        </div>
        <div class="col-md-4">
        <form action="/workhours" method="post">
            <div class="col-md-3">
                Od
                <input name="from" type="date">
            </div>
            <div class="col-md-3">
                Do
                <input name="to" type="date">
            </div>
            <div class="col-md-3">
                <input type="submit" value="Szukaj">
            </div>
        </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-5">
            <h5>Wyniki dla okresu ${from} - ${to}</h5>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5">
                <table class="table table-sm table-bordered">
                    <thead>
                        <tr>
                            <th>Pracownik</th>
                            <th>Liczba przepracowanych roboczogodzin</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${employees}" var="employee">
                            <c:set var="employee" scope="request" value="${employee}" />
                            <tr>
                                <td>${employee.first_name} ${employee.last_name}</td>
                                <% OrderDao odao = new OrderDao();
                                    Employee employee = (Employee) request.getAttribute("employee");
                                    Order[] orders = odao.findAllByEmployee(employee.getId());
                                    int hours=0;
                                    int MILLIS_PER_H = 1000*60*60;
                                    long deltaH=0;
                                    Calendar calendar = Calendar.getInstance();
                                    java.util.Date currentDate = calendar.getTime();
                                    java.sql.Date now = new java.sql.Date(currentDate.getTime());
                                    Date startDate;
                                    Date from=Date.valueOf("1666-06-06");
                                    Date to=Date.valueOf("1666-06-06");
                                    String fromString="";
                                    String toString="";
                                    if (request.getAttribute("from")!=null) {
                                        fromString=(String)request.getAttribute("from");
                                        if (!fromString.equals("")) from=Date.valueOf(fromString);
                                    }
                                    if (request.getAttribute("to")!=null) {
                                        toString =(String)request.getAttribute("to");
                                        if (!toString.equals("")) to=Date.valueOf(toString);
                                    }
                                    for (Order o : orders) {
                                        if (from.equals(Date.valueOf("1666-06-06")) && o.getActual_start() != null)
                                            startDate = o.getActual_start();
                                        else if (from.equals(Date.valueOf("1666-06-06")) && o.getActual_start() == null)
                                            startDate = Date.valueOf(LocalDate.now());
                                        else startDate = from;
                                        if (!to.equals(Date.valueOf("1666-06-06"))) now = to;
                                        else to = Date.valueOf(LocalDate.now());
                                        LocalDate start = startDate.toLocalDate();
                                        LocalDate end = now.toLocalDate();
                                        if (o.getActual_start() != null && from.before(o.getActual_start()))
                                            startDate = o.getActual_start();
                                        else startDate = Date.valueOf(LocalDate.now());
                                        if (to.after(Date.valueOf(LocalDate.now()))) to = Date.valueOf(LocalDate.now());
                                        deltaH = (to.getTime() - startDate.getTime()) * 8 / 24 / MILLIS_PER_H;
                                        for (LocalDate date = startDate.toLocalDate(); date.isBefore(to.toLocalDate()); date = date.plusDays(1)) {
                                            Calendar day = Calendar.getInstance();
                                            day.setTime(Date.valueOf(date));
                                            if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                                                    day.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                                                deltaH -= 8;
                                                System.out.print("*");
                                            }
                                        }
                                        if (deltaH > 0) hours += deltaH;
                                    }
                                    request.setAttribute("hours", hours); %>
                                <td>${hours}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>
