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
            <h4>Raport zysków</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/';">
                Powrót
            </button>
        </div>
        <div class="col-md-4">
            <form action="/income" method="post">
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
    <c:if test="${(not empty from) || (not empty to)}">
    <div class="row">
        <div class="col-md-5">
            <h5>Wyniki dla okresu ${from} - ${to}</h5><br>
            <h6>(tylko zlecenia rozpoczęte i zakończone w tym okresie)</h6>
        </div>
    </div>
    </c:if>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5">
                <table class="table table-sm table-bordered">
                    <thead>
                    <tr>
                        <th>Id zlecenia</th>
                        <th>Przychody [zł]</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% int sum=0; %>
                    <c:forEach items="${orders}" var="order">
                        <c:set var="order" scope="request" value="${order}" />
                        <tr>
                            <td><a href="/orderdetails?id=${order.id}">${order.id}</a></td>
                            <% Order order=null;
                            if (request.getAttribute("order")!=null) order=(Order)request.getAttribute("order");
                                sum+=order.getIncome(); %>
                            <td>${order.income}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Suma</td>
                        <% request.setAttribute("sum", sum); %>
                        <td>${sum}</td>
                    </tr>
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
