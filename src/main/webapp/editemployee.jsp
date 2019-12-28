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
            <h4>Edytuj pracownika</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/employees';">
                Powrót
            </button>
        </div>
    </div>
    <form role="form" action="/employees" method="post">
        <% Employee editedEmployee = (Employee) request.getAttribute("editedEmployee"); %>
        <input value="${editedEmployee.id}" hidden name="id">
        <div class="form-group">

            <label for="firstName">
                Imię
            </label>
            <input value="${editedEmployee.first_name}" type="text" class="form-control" id="firstName" name="firstName" />
        </div>
        <div class="form-group">

            <label for="lastName">
                Nazwisko
            </label>
            <input value="${editedEmployee.last_name}" type="text" class="form-control" id="lastName" name="lastName" />
        </div>
        <div class="form-group">

            <label for="address">
                Adres
            </label>
            <input value="${editedEmployee.address}" type="text" class="form-control" id="address" name="address" />
        </div>

        <div class="form-group">

            <label for="phone">
                Telefon
            </label>
            <input value="${editedEmployee.phone}" type="number" class="form-control" id="phone" name="phone" />
        </div>
        <div class="form-group">

            <label for="note">
                Notatka
            </label>
            <textarea rows="5" type="text" class="form-control" id="note" name="note">
                ${editedEmployee.note}
            </textarea>
        </div>
        <div class="form-group">

            <label for="pay_h">
                Stawka godzinowa [zł]
            </label>
            <input value="${editedEmployee.pay_h}" type="number" class="form-control" id="pay_h" name="pay_h" />
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
