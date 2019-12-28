<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.workshop.models.Employee" %>
<%@ page import="org.workshop.models.Status" %>
<%@ page import="org.workshop.models.Order" %>
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
            <h4>Edytuj zlecenie</h4>
        </div>
            <div class="col-md-4">
                <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/orders';">
                    Powrót
                </button>
        </div>
    </div>
            <form role="form" action="/orders" method="post">
                <% Order editedOrder = (Order) request.getAttribute("editedOrder"); %>
                <input value="${editedOrder.id}" hidden name="id">
                <div class="form-group">

                    <label for="planStartDate">
                        Planowana data rozpoczęcia naprawy
                    </label>
                    <input value="${editedOrder.plan_start}" type="date" class="form-control" id="planStartDate" name="planStartDate" />
                </div>
                <div class="form-group">

                    <label for="startDate">
                        Data rozpoczęcia naprawy
                    </label>
                    <input value="${editedOrder.actual_start}" type="date" class="form-control" id="startDate" name="startDate" />
                </div>
                <div class="form-group">

                    <label for="end">
                        Data zakończenia naprawy
                    </label>
                    <input value="${editedOrder.end}" type="date" class="form-control" id="end" name="end" />
                </div>
                <div class="form-group">

                    <label for="employee">
                        Przypisany do naprawy pracownik
                    </label>
                    <select class="form-control" id="employee" name="employee">
                        <c:forEach items="${employees}" var="employee">
                        <option <c:if test="${userProfile.state == 'AK'}">selected="selected"</c:if> value="${employee.id}">${employee.first_name} ${employee.last_name}</option>
                        </c:forEach>
                    </select>

                    <label for="descriptionProblem">
                        Opis problemu
                    </label>
                    <textarea rows="5" type="text" class="form-control" id="descriptionProblem" name="descriptionProblem">
                        ${editedOrder.description_problem}
                    </textarea>
                </div>
                <div class="form-group">

                    <label for="descriptionRepair">
                        Opis naprawy
                    </label>
                    <textarea rows="5" type="text" class="form-control" id="descriptionRepair" name="descriptionRepair">
                        ${editedOrder.description_repair}
                    </textarea>
                </div>
                <div class="form-group">

                    <label for="vehicle">
                        Pojazd którego dotyczy naprawa
                    </label>
                    <select class="form-control" id="vehicle" name="vehicle">
                        <c:forEach items="${vehicles}" var="vehicle">
                            <option  value="${vehicle.id}" <c:if test="${editedOrder.vehicle_id == vehicle.id}">selected</c:if>>${vehicle.make} ${vehicle.model}</option>
                        </c:forEach>
                    </select>

                    <div class="form-group">

                        <label for="priceCustomer">
                            Koszt naprawy dla klienta [zł]
                        </label>
                        <input value="${editedOrder.price_customer}" type="number" class="form-control" id="priceCustomer" name="priceCustomer" />
                    </div>

                    <div class="form-group">

                        <label for="costParts">
                            Koszt wykorzystanych części [zł]
                        </label>
                        <input value="${editedOrder.cost_parts}" type="number" class="form-control" id="costParts" name="costParts" />
                    </div>

                    <div class="form-group">

                        <label for="h">
                            Liczba roboczogodzin
                        </label>
                        <input value="${editedOrder.h}" type="number" class="form-control" id="h" name="h" />
                    </div>

                    <button type="submit" class="btn btn-primary">
                        Edytuj
                    </button>
            </form>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
