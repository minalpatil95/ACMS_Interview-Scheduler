<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 21/3/18
  Time: 12:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>INTERVIEW SCHEDULER</title>

        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <form class="form-horizontal">
            <fieldset>

                <!-- Form Name -->
                <legend>Login</legend>

                <!-- Dropdown-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="dropdown">Employee type</label>
                    <select id="dropdown" name="dropdown">
                        <option value="1">Recruiter</option>
                        <option value="2">Interviewer</option>
                    </select>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="username">Username</label>
                    <div class="col-md-4">
                        <input id="username" name="username" type="text" placeholder="Username" class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Password</label>
                    <div class="col-md-4">
                        <input id="password" name="password" type="password" placeholder="Password" class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="btnSubmit"></label>
                    <div class="col-md-4">
                        <button id="btnSubmit" name="btnSubmit" class="btn btn-primary">Submit</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </body>
</html>

