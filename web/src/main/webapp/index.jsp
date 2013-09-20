<!doctype html>

<html>
<head>
    <meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8” />
    <title>Symulacja Datastore</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootswatch/3.0.0/cosmo/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
    <link href="" rel="stylesheet" type="text/css">
</head>

<body>
<div class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header"></div>
            <a class="navbar-brand">SERWER DATASTORE<i class="icon-lightbulb pull-left icon-large"></i></a>
        </div>
</div>

<div class="container">
    <div class="row">
        <div class="span8 offset2">
            <div class="page-header">
                <h1>Symulacja logowania danych z sieci</h1>
            </div>
            <div style="margin-bottom: 20px">
                RabbitMQ
                <div class="alert alert-success">
                Message Broker rozwijany przez VMWare. Implementacja standardu AMQP, pozwalająca na asynchroniczną obsługę połączeń webowych.
                </div>
                SpringMVC
                <div class="alert alert-success">
                Framework MVC będący częścią frameworku Spring. Pozwala na użycie mapowiania requestów i dependency injection w serwisach WWW.
                </div>
                Redis
                <div class="alert alert-success">
                    Redis to bardzo prosta baza w pamięci zainspirowana przez memcache. Do struktury klucz-> wartość nakłada dodatkowe struktury danych i operacje atomowe na nich. Pokażę jak mechanizmy transakcji i atomowość pomagają przekładać ciężar niektórych algorytmów do Redisa.
                </div>
                </ul>
            </div>
            <a href="/spring/symuluj" class="btn btn-primary btn-large">Uruchom symulacje</a>
        </div>

    </div>
    <div class="row" style="margin-top:10px">Wykonane przez: Artur Skowronski & Pawel Nester</div>

</div>
<script src="/resources/js/jquery-1.7.1.min.js"></script>
</body>
</html>
