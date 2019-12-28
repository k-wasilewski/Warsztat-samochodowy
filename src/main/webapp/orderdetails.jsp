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
        <div class="col-md-8">
            <h4>Szczegóły zlecenia</h4>
    </div>
    <div class="col-md-4">
        <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/orders';">
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
                            <tr>
                                <td>Id</td>
                                <td>${order.id}</td>
                            </tr>
                            <tr>
                                <td>Data przyjęcia do naprawy</td>
                                <td>${order.created}</td>
                            </tr>
                            <tr>
                                <td>Planowana data rozpoczęcia naprawy</td>
                                <td>${order.plan_start}</td>
                            </tr>
                            <tr>
                                <td>Data rozpoczęcia naprawy</td>
                                <td>${order.actual_start}</td>
                            </tr>
                            <tr>
                                <td>Data zakończenia naprawy</td>
                                <td>${order.end}</td>
                            </tr>
                            <tr>
                                <td>Pracownik przypisany do naprawy</td>
                                <% EmployeeDao edao = new EmployeeDao();
                                int employeeId = order.getEmployee_id();
                                Employee employee = edao.read(employeeId);
                                request.setAttribute("employee", employee);%>
                                <td>${employee.first_name} ${employee.last_name}</td>
                                <td><button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/employeedetails?id=${employee.id}';">
                                    Szczegóły
                                </button></td>
                            </tr>
                            <tr>
                                <td>Opis problemu</td>
                                <td>${order.description_problem}</td>
                            </tr>
                            <tr>
                                <td>Opis naprawy</td>
                                <td>${order.description_repair}</td>
                            </tr>
                            <tr class="${rowStyle}">
                                <td>Status</td>
                                <td>${statusDesc}</td>
                            </tr>
                            <tr>
                                <td>Pojazd którego dotyczy naprawa</td>
                                <% VehicleDao vdao = new VehicleDao();
                                Vehicle vehicle = vdao.read(order.getVehicle_id());
                                request.setAttribute("vehicle", vehicle);%>
                                <td>${vehicle.make} ${vehicle.model}</td>
                                <td><button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/vehicledetails?id=${vehicle.id}';">
                                    Szczegóły
                                </button></td>
                            </tr>
                            <tr>
                                <td>Koszt naprawy dla klienta</td>
                                <td>${order.price_customer}</td>
                            </tr>
                            <tr>
                                <td>Koszt wykorzystanych części</td>
                                <td>${order.cost_parts}</td>
                            </tr>
                            <tr>
                                <td>Koszt roboczogodziny</td>
                                <td><c:set var="order" scope="request" value="${order}"></c:set>
                                    ${employee.pay_h}</td>
                            </tr>
                            <tr>
                                <td>Liczba roboczogodzin</td>
                                <td>${order.h}</td>
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
