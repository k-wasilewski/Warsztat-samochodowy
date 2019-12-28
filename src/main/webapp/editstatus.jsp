<%@ page import="org.workshop.models.Status" %>
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

<div class="container-fluid">
    <div class="row">
        <div class="col-md-5">
            <h4>Edytuj status</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-info btn-md" onclick="window.location.href = '/orders';">
                Powrót
            </button>
        </div>
    </div>
            <form role="form" action="/orders" method="post">

                    <label for="status">
                        Status zlecenia
                    </label>
                <select class="form-control" id="status" name="status">
                    <c:forEach items="${statuses}" var="status">
                        <c:set var="status" scope="request" value="${status}" />
                        <% Status status = (Status) request.getAttribute("status");
                            String statusDesc="";
                            switch (status.getStatus()) {
                                case 1:
                                    statusDesc="Przyjęty";
                                    break;
                                case 2:
                                    statusDesc="Zatwierdzone koszty naprawy";
                                    break;
                                case 3:
                                    statusDesc="W naprawie";
                                    break;
                                case 4:
                                    statusDesc="Gotowy do odbioru";
                                    break;
                                case 5:
                                    statusDesc="Rezygnacja";
                                    break;
                            }
                            request.setAttribute("statusDesc", statusDesc);%>
                        <option <c:if test="${editedStatus.id == status.id}">selected</c:if> value="${status.id}">${statusDesc}</option>
                    </c:forEach>
                </select>


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
