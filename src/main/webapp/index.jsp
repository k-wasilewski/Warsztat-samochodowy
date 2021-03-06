<%@ page import="org.workshop.dao.StatusDao" %>
<%@ page import="org.workshop.models.Status" %>
<%@ page import="org.workshop.models.Order" %>
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
                <div class="col-md-8" style="margin: 0 auto; float: none;">
                    <div class="carousel slide" id="carousel-289860">
                        <ol class="carousel-indicators">
                            <li data-slide-to="0" data-target="#carousel-289860">
                            </li>
                            <li data-slide-to="1" data-target="#carousel-289860" class="active">
                            </li>
                            <li data-slide-to="2" data-target="#carousel-289860">
                            </li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item">
                                <img class="d-block w-100" alt="Carousel Bootstrap First" src="images/kiev-ukraine-21-august-2019-600w-1502297192.jpg" />
                                <div class="carousel-caption">
                                    <h1>
                                        Warsztat samochodowy
                                    </h1>
                                    <h5>
                                        Projekt z wykorzystaniem Bootstrap, JSP, JDBC
                                    </h5>
                                </div>
                            </div>
                            <div class="carousel-item active">
                                <img class="d-block w-100" alt="Carousel Bootstrap Second" src="images/stock-photo-empty-workshop-with-a-lift-in-a-car-repair-station-542644894.jpg" />
                                <div class="carousel-caption">
                                    <h1>
                                        Warsztat samochodowy
                                    </h1>
                                    <h5>
                                        Projekt z wykorzystaniem Bootstrap, JSP, JDBC
                                    </h5>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" alt="Carousel Bootstrap Third" src="images/wheel-alignment-car-on-stand-600w-1575585406.jpg" />
                                <div class="carousel-caption">
                                    <h1>
                                        Warsztat samochodowy
                                    </h1>
                                    <h5>
                                        Projekt z wykorzystaniem Bootstrap, JSP, JDBC
                                    </h5>
                                </div>
                            </div>
                        </div> <a class="carousel-control-prev" href="#carousel-289860" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-289860" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
                    </div>
                </div>
            </div>


        <%@ include file="footer.jsp" %>
        </div>
</body>
</html>
