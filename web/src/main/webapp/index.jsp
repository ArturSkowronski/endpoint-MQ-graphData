<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <title>Symulacja </title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootswatch/3.0.0/cosmo/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css">
    <link href="" rel="stylesheet" type="text/css">

    <!--
    IMPORTANT:
    This is Heroku specific styling. Remove to customize.
    -->
    <link href="/resources/css/heroku.css" rel="stylesheet">
    <style type="text/css">
        .instructions {
            display: none;
        }

        .instructions li {
            margin-bottom: 10px;
        }

        .instructions h2 {
            margin: 18px 0;
        }

        .instructions blockquote {
            margin-top: 10px;
        }

        .screenshot {
            margin-top: 10px;
            display: block;
        }

        .screenshot a {
            padding: 0;
            line-height: 1;
            display: inline-block;
            text-decoration: none;
        }

        .screenshot img, .tool-choice img {
            border: 1px solid #ddd;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
            -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
        }
    </style>
    <!-- /// -->
    <script type="text/javascript">
        <!--
        function appname() {
            return location.hostname.substring(0, location.hostname.indexOf("."));
        }
        // -->
    </script>
</head>

<body>
<div class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header"></div>
            <a class="navbar-brand">SERWER DATASTORE<i class="icon-lightbulb pull-left icon-large white"</a>
        </div>
</div>

<div class="container" id="getting-started">
    <div class="row">
        <div class="span8 offset2">

            <div class="page-header">
                <h1>Symulacja logowania danych z sieci</h1>
            </div>

            <div style="margin-bottom: 20px">
                  <ul>
                      <li>RabbitMQ</li>
                      <li>SpringMVC</li>
                      <li>Hadoop</li>
                  </ul>
            </div>


            <a href="/spring/bigOp" class="btn btn-primary btn-large">Uruchom symulacje</a>
        </div>
    </div>
</div>
<script src="/resources/js/jquery-1.7.1.min.js"></script>
<script src="/resources/js/bootstrap-modal.js"></script>
<script src="/resources/js/bootstrap-tab.js"></script>
</body>
</html>
