<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Symulacja uruchomiona</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootswatch/3.0.0/cosmo/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
</head>

<body>
    <div class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand">SYMULACJA DATASTORE<i class="icon-lightbulb pull-left icon-large"></i></a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="span8 offset2">
                <div class="page-header">
                    <h1>Symulacja trwa...</h1>
                    ${bigOp}
                </div>
            </div>
        </div>
        <div class="row" style="margin-top:10px">Wykonane przez: Artur Skowronski & Paweł Nester</div>
    </div>

    <script src="/resources/js/jquery-1.7.1.min.js"></script>
</body>
</html>
