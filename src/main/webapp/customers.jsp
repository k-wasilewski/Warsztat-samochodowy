<%@ page import="org.workshop.dao.StatusDao" %>
<%@ page import="org.workshop.dao.EmployeeDao" %>
<%@ page import="org.workshop.dao.VehicleDao" %>
<%@ page import="org.workshop.models.*" %>
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
        <div class="col-md-3">
            <h4>Aktualnie obsługiwani klienci</h4>
        </div>
        <div class="col-md-3">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/addcustomer';">
            Dodaj
            </button>
        </div>
        <form action="/customers" method="post">
            <div class="col-md-3">
                <input name="search" type="text">
            </div>
            <div class="col-md-3">
                <input type="submit" value="Szukaj">
            </div>
        </form>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <table class="table table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>
                                Imię
                            </th>
                            <th>
                                Nazwisko
                            </th>
                            <th>

                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${customers}" var="customer">
                            <c:set var="customer" value="${customer}" scope="request"/>
                            <%  Customer customer = (Customer) request.getAttribute("customer"); %>
                                <td>
                                        ${customer.first_name}
                                </td>
                                <td>
                                    ${customer.last_name}
                                </td>
                                <td>
                                    <a href="/delcustomer?id=${customer.id}"><button type="button" onclick="return confirm('Czy na pewno chcesz usunąć?')" class="btn btn-default btn-md">
                                        Usuń
                                    </button></a>
                                    <button type="button" onclick="window.location.href = '/editcustomer?id=${customer.id}';" class="btn btn-success btn-md">
                                        Edytuj
                                    </button>
                                    <button type="button" onclick="window.location.href = '/addvehicletocustomer?id=${customer.id}';" class="btn btn-info btn-md">
                                        Dodaj pojazd
                                    </button>
                                    <button type="button" onclick="window.location.href = '/customerdetails?id=${customer.id}';" class="btn btn-danger btn-md">
                                        Szczegóły
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>
