<%@ page import="org.workshop.dao.StatusDao" %>
<%@ page import="org.workshop.models.Status" %>
<%@ page import="org.workshop.models.Order" %>
<%@ page import="org.workshop.models.Employee" %>
<%@ page import="org.workshop.dao.EmployeeDao" %>
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
            <h4>Szczegóły pracownika</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/employees';">
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
                        <td>${employee.id}</td>
                    </tr>
                    <tr>
                        <td>Imię</td>
                        <td>${employee.first_name}</td>
                    </tr>
                    <tr>
                        <td>Nazwisko</td>
                        <td>${employee.last_name}</td>
                    </tr>
                    <tr>
                        <td>Adres</td>
                        <td>${employee.address}</td>
                    </tr>
                    <tr>
                        <td>Telefon</td>
                        <td>${employee.phone}</td>
                    </tr>
                    <tr>
                        <td>Notatka</td>
                        <td>${employee.note}</td>
                    </tr>
                    <tr>
                        <td>Stawka godzinowa</td>
                        <td>${employee.pay_h}</td>
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
