<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.workshop.models.Employee" %>
<%@ page import="org.workshop.models.Status" %>
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
            <h4>Dodaj nowe zlecenie</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/employees';">
                Powrót
            </button>
        </div>
    </div>
    <div class="col-md-5">
        <form role="form" action="/orders" method="post">
            <div class="form-group">

                <label for="planStartDate">
                    Planowana data rozpoczęcia naprawy
                </label>
                <input type="date" class="form-control" id="planStartDate" name="planStartDate" />
            </div>
            <div class="form-group">

                <label for="startDate">
                    Data rozpoczęcia naprawy
                </label>
                <input type="date" class="form-control" id="startDate" name="startDate" />
            </div>
            <div class="form-group">

                <label for="employee">
                    Przypisany do naprawy pracownik
                </label>
                <br>
                <input hidden name="employee" id="employee" value="${employee.id}">
                <h5>${employee.first_name} ${employee.last_name}</h5>
                <br>

                <label for="descriptionProblem">
                    Opis problemu
                </label>
                <textarea rows="5" type="text" class="form-control" id="descriptionProblem" name="descriptionProblem"></textarea>
            </div>
            <div class="form-group">

                <label for="descriptionRepair">
                    Opis naprawy
                </label>
                <textarea rows="5" type="text" class="form-control" id="descriptionRepair" name="descriptionRepair"></textarea>
            </div>
            <div class="form-group">

                <input value="1" hidden name="status" id="status">

                <label for="vehicle">
                    Pojazd którego dotyczy naprawa
                </label>
                <select class="form-control" id="vehicle" name="vehicle">
                    <c:forEach items="${vehicles}" var="vehicle">
                        <option  value="${vehicle.id}">${vehicle.make} ${vehicle.model}</option>
                    </c:forEach>
                </select>

                <div class="form-group">

                    <label for="priceCustomer">
                        Koszt naprawy dla klienta [zł]
                    </label>
                    <input type="number" class="form-control" id="priceCustomer" name="priceCustomer" />
                </div>

                <div class="form-group">

                    <label for="costParts">
                        Koszt wykorzystanych części [zł]
                    </label>
                    <input type="number" class="form-control" id="costParts" name="costParts" />
                </div>

                <div class="form-group">

                    <label for="h">
                        Liczba roboczogodzin
                    </label>
                    <input type="number" class="form-control" id="h" name="h" />
                </div>

                <button type="submit" class="btn btn-primary">
                    Dodaj
                </button>
        </form>
    </div>
</div>
</div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
