<%@ page import="org.workshop.dao.StatusDao" %>
<%@ page import="org.workshop.models.Status" %>
<%@ page import="org.workshop.models.Order" %>
<%@ page import="org.workshop.models.Employee" %>
<%@ page import="org.workshop.dao.EmployeeDao" %>
<%@ page import="org.workshop.models.Customer" %>
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
        <div class="col-md-8">
            <h4>Szczegóły pojazdu</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/vehicles';">
                Powrót
            </button>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5">
                <table class="table table-sm table-bordered">
                    <thead>
                    <tbody>
                    <tr>
                        <td>Id</td>
                        <td>${vehicle.id}</td>
                    </tr>
                    <tr>
                        <td>Marka</td>
                        <td>${vehicle.make}</td>
                    </tr>
                    <tr>
                        <td>Model</td>
                        <td>${vehicle.model}</td>
                    </tr>
                    <tr>
                        <td>Data produkcji</td>
                        <td>${vehicle.dop}</td>
                    </tr>
                    <tr>
                        <td>Nr rejestracyjny</td>
                        <td>${vehicle.lic}</td>
                    </tr>
                    <tr>
                        <td>Data następnego serwisu</td>
                        <td>${vehicle.next_service}</td>
                    </tr>
                    <tr>
                        <td>Właściciel</td>
                        <% Customer owner = (Customer) request.getAttribute("owner"); %>
                        <td><a href="/customerdetails?id=${owner.id}">${owner.first_name} ${owner.last_name}</a></td>
                    </tr>
                    <tr>
                        <td>Zlecenia</td>
                        <td>
                                <table>
                                    <body>
                                    <c:forEach items="${orders}" var="order">
                                    <c:set var="order" value="${order}" scope="request"/>
                                    <% Order order = (Order) request.getAttribute("order"); %>
                                    <tr>
                                        <div class="col-md-2"><td>${order.actual_start}</td></div>
                                        <div class="col-md-2"><td>${order.description_repair}</td></div>
                                        <div class="col-md-2"><td><button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/orderdetails?id=${order.id}';">
                                            Szczegóły
                                        </button></td></div>
                                    </tr>
                                    </c:forEach>
                                    </body>
                                </table>
                        </td>
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
