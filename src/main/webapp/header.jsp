<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Bootstrap 4, from LayoutIt!</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>
<body>
<header>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-5">
            <span class="badge badge-default" style="font-size:35px;">Warsztat samochodowy</span>
        </div>
        <div class="col-md-2">

            <button type="button" class="btn btn-primary btn-md" onclick="window.location.href = '/';">
                Strona główna
            </button>
        </div>
        <div class="col-md-2">

            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Raporty
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="/workhours">Raport przepracowanych godzin</a></li>
                    <li><a href="/income">Raport zysków</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-2">

            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Listy
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="/customers">Lista klientów</a></li>
                    <li><a href="/orders">Lista zleceń</a></li>
                    <li><a href="/employees">Lista pracowników</a></li>
                    <li><a href="/vehicles">Lista pojazdów</a></li>
                </ul>
            </div>
        </div>
        </div>
        </div>
        <div class="row">
            <div class="col-md-5"></div>
            <div class="col-md-2">

                <button type="button" class="btn btn-primary">
                    About
                </button>
            </div>
            <div class="col-md-2">

            <button type="button" class="btn btn-primary">
                Contact
            </button>
        </div>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</header>
</body>
</html>