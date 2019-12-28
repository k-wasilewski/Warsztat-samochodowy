<%@ page import="org.workshop.dao.StatusDao" %>
<%@ page import="org.workshop.models.Status" %>
<%@ page import="org.workshop.models.Order" %>
<%@ page import="org.workshop.models.Employee" %>
<%@ page import="org.workshop.dao.EmployeeDao" %>
<%@ page import="org.workshop.dao.VehicleDao" %>
<%@ page import="org.workshop.models.Vehicle" %>
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
            <h4>Aktualnie prowadzone naprawy</h4>
        </div>
        <div class="col-md-8">
            <div class="row">
                <div class="col-md-4">
                    <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/addorder';">
                        Dodaj
                    </button>
                </div>

            </div>
        </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <table class="table table-sm table-bordered">
                    <thead>
                    <tr>
                        <th>
                            Data przyjęcia do naprawy
                        </th>
                        <th>
                            Przypisany do naprawy pracownik
                        </th>
                        <th>
                            Opis problemu
                        </th>
                        <th>
                            Status
                        </th>
                        <th>
                            Pojazd którego dotyczy naprawa
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                        <c:set var="order" value="${order}" scope="request"/>
                        <%  String statusDesc="";
                            String rowStyle="";
                            StatusDao sdao = new StatusDao();
                            Order order = (Order) request.getAttribute("order");
                            Status status = (Status) sdao.read(order.getStatus_id());
                            switch (status.getStatus()) {
                                case 1:
                                    statusDesc="Przyjęty";
                                    rowStyle="table-active";
                                    break;
                                case 2:
                                    statusDesc="Zatwierdzone koszty naprawy";
                                    rowStyle="";
                                    break;
                                case 3:
                                    statusDesc="W naprawie";
                                    rowStyle="table-danger";
                                    break;
                                case 4:
                                    statusDesc="Gotowy do odbioru";
                                    rowStyle="table-success";
                                    break;
                                case 5:
                                    statusDesc="Rezygnacja";
                                    rowStyle="table-warning";
                                    break;
                            }
                            request.setAttribute("statusDesc", statusDesc);
                            request.setAttribute("rowStyle", rowStyle);%>
                        <tr class="${rowStyle}">
                            <td>
                                ${order.created}
                            </td>
                            <td>
                                <% EmployeeDao edao = new EmployeeDao();
                                    Employee employee = edao.read(order.getEmployee_id());
                                    request.setAttribute("employee", employee); %>
                                ${employee.first_name} ${employee.last_name}
                            </td>
                            <td>
                                ${order.description_problem}
                            </td>
                            <td>
                                    ${statusDesc}
                            </td>
                            <td>
                                <% VehicleDao vdao = new VehicleDao();
                                    Vehicle vehicle = vdao.read(order.getVehicle_id());
                                    request.setAttribute("vehicle", vehicle); %>
                                ${vehicle.make} ${vehicle.model}
                            </td>
                            <td>
                                <a href="/delorder?id=${order.id}"><button type="button" onclick="return confirm('Czy na pewno chcesz usunąć?')" class="btn btn-default btn-md">
                                    Usuń
                                </button></a>
                                <button type="button" onclick="window.location.href = '/editorder?id=${order.id}';" class="btn btn-success btn-md">
                                    Edytuj
                                </button>
                                <button type="button" onclick="window.location.href = '/editstatus?id=${order.id}';" class="btn btn-warning btn-md">
                                    Zmień status
                                </button>
                                <button type="button" onclick="window.location.href = '/orderdetails?id=${order.id}';" class="btn btn-danger btn-md">
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
