<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 22/3/18
  Time: 4:42 PM
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
        <legend>Create Event</legend>

        <%--Nav Bar--%>
        <div class="row bar">
            <nav class="navbar navbar-default navbar-fixed">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collection of nav links and other content for toggling -->
                    <div id="navbarCollapse" class="collapse navbar-collapse">
                        <ul class="nav nav-tabs">
                            <li><a href="homepageRecruiter.jsp">Home</a></li>
                            <li><a href="profileRecruiter.jsp">Profile</a></li>
                            <li  class="active"><a href="createEvent.jsp">Create new Event</a></li>
                            <li><a href="#"> Logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="eventName">Event Name</label>
            <div class="col-md-4">
                <input id="eventName" name="eventName" type="text" placeholder="eventName" class="form-control input-md" required="">
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="eventType">Event Type</label>
            <select id="eventType" name="eventType">
                <option value="1">Hiring</option>
                <option value="2">Onsite</option>
            </select>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="eventDate">Event Date</label>
            <div class="col-md-4">
                <input id="eventDate" name="eventDate" type="date" class="form-control input-md" required="">
            </div>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label">Competency</label>
            <input type="checkbox" name="sports" id="c++">
            <label for="c++">C++</label>
            <input type="checkbox" name="sports" id="java">
            <label for="java">Java</label>
            <input type="checkbox" name="sports" id="python">
            <label for="python">Python</label>
        </div>


        <div class="form-group">
            <label class="col-md-4 control-label" for="jobLevel">Job Level</label>
            <select id="jobLevel" name="jobLevel">
                <option value="1">Job Level 1</option>
                <option value="2">Job Level 2</option>
            </select>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="number">No. of candidates to be interviewed</label>
            <div class="col-md-4">
                <input id="number" name="number" type="number" placeholder="No. of candidates" class="form-control input-md" required="">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="round">No. of rounds to be conducted</label>
            <div class="col-md-4">
                <input id="round" name="round" type="number" placeholder="No. of rounds" class="form-control input-md" required="">
            </div>
        </div>


        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="submit"></label>
            <div class="col-md-4">
                <button id="submit" name="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="phnScreening"></label>
            <div class="col-md-4">
                <button id="phnScreening" name="phnScreening" class="btn btn-primary">Schedule Phone Screening</button>
            </div>
        </div>

    </fieldset>
</form>
</body>
</html>


