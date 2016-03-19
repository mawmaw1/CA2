<%-- 
    Document   : login
    Created on : 18-Feb-2016, 10:05:47
    Author     : ingim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="newcss.css">
        <title>CA2</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <style>
            .randomShizznix{
                padding-top: 80px;
                padding-bottom: 30px;

            }
            html {
                overflow-y: scroll; 
            }
            html, body, .container-table {
                height: 100%;
            }
            .container-table {
                display: table;
            }
            .vertical-center-row {
                display: table-cell;
                vertical-align: middle;
            }
            input[type="text"]{
                padding: 10px;
            }
            input[type="password"]{
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container container-table">
            <div class="row vertical-center-row">
                <div class="text-center col-md-4 col-md-offset-4">
                    <h2>Login</h2>
                    <form action="j_security_check" class="form-group">
                        <input type="text" name="j_username" class="btn-block" placeholder="Username">
                        <br>
                        <br>
                        <input type="password" name="j_password" class="btn-block" placeholder="Password">
                        <br>
                        <br>
                        <input type="submit" value="Login" class="btn btn-primary btn-block">
                        <br>
                        <br>
                        
                        <form action="index.html" method="get">
                            <input type="submit" value="Back!" class="btn btn-primary btn-block">
                        </form>
                    </form>
                    <br>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                if (sessionStorage.getItem("is_reloaded"))
                    alert('Error with login!');

                sessionStorage.setItem("is_reloaded", true);
            });
        </script>
    </body>
</html>
