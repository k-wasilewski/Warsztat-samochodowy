<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.workshop.models.Employee" %>
<%@ page import="org.workshop.models.Status" %>
<%@ page import="org.workshop.models.Vehicle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="container-fluid">
    <div class="row">
        <div class="col-md-5">
            <h4>Dodaj pojazd</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/vehicles';">
                Powrót
            </button>
        </div>
    </div>
    <div class="col-md-5">
        <form role="form" action="/vehicles" method="post">
            <% Vehicle editedVehicle = (Vehicle) request.getAttribute("editedVehicle"); %>
            <input hidden value="${editedVehicle.id}" name="id">
            <div class="form-group">

                <label for="make">
                    Marka
                </label>
                <input value="${editedVehicle.make}" type="text" class="form-control" id="make" name="make" />
            </div>
            <div class="form-group">

                <label for="model">
                    Model
                </label>
                <input value="${editedVehicle.model}" type="text" class="form-control" id="model" name="model" />
            </div>
            <div class="form-group">

                <label for="dateOfProduction">
                    Data produkcji
                </label>
                <input value="${editedVehicle.dop}" type="date" class="form-control" id="dateOfProduction" name="dateOfProduction" />
            </div>

            <div class="form-group">

                <label for="license">
                    Nr rejestracyjny
                </label>
                <input value="${editedVehicle.lic}" type="text" class="form-control" id="license" name="license" />
            </div>
            <div class="form-group">

                <label for="nextService">
                    Najbliższy serwis
                </label>
                <input value="${editedVehicle.next_service}" type="date" class="form-control" id="nextService" name="nextService" />
            </div>
            <div class="form-group">

                <label for="customer">
                    Właściciel
                </label>
                <select class="form-control" id="customer" name="customer">
                    <c:forEach items="${customers}" var="customer">
                        <option <c:if test="${editedVehicle.customer_id == customer.id}">selected</c:if> value="${customer.id}">${customer.first_name} ${customer.last_name}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">
                Edytuj
            </button>
        </form>
    </div>
</div>
</div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
