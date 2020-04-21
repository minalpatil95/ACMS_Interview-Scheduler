<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 23/5/18
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.minal.scheduler.utils.TokenAuth" %>
<%@ page import="com.minal.scheduler.model.Employee" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.minal.scheduler.model.Event" %>
<%@ page import="com.minal.scheduler.model.Round" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>INTERVIEW SCHEDULER</title>

    <meta charset="utf-8">
    <%--<title dir="ltr">Amazon Sign IN</title>--%>
    <%Cookie[] cookies = request.getCookies();
        Employee employee = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("authToken")){
                    employee = TokenAuth.getEmployeeFromToken(cookie.getValue());
                }
            }
        }%>
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <!-- <link rel="stylesheet" href="https://images-na.ssl-images-amazon.com/images/I/61gbb09bfIL._RC|11Fd9tJOdtL.css,21ULbzscqzL.css,31Q3id-QR0L.css,31QszevPBSL.css_.css#AUIClients/AmazonUI.min" /> -->
    <link rel="stylesheet" href="../css/amazon1.css"/>
    <link rel="stylesheet" href="../css/amazon2.css"/>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/selectInterviewers.js"></script>
    <script src="../js/handlebars-v4.0.11.js"></script>
    <script src="../js/url-v2.5.2.js"></script>
    <script src="../js/createEvent.js"></script>
    <% Collection<Employee> interviewers = (Collection<Employee>) request.getAttribute("interviewers");%>
    <% Collection<Round> rounds = (Collection<Round>) request.getAttribute("rounds");%>
    <%Event event = (Event)request.getAttribute("event"); %>

    <style>
        label { width: 200px; float: left; margin: 0 20px 0 0; }
        span { display: block; margin: 0 0 3px; }
        input { width: 200px; border: 1px solid #000; padding: 5px; }
    </style>
</head>
<body>
<div class="container">
    <!--Header-->
    <div class="row">
        <div class="page-header">
            <h1 style="color: #1b6d85; display: inline-block;">Interview Scheduler</h1>
            <div style="text-align:right; float: right;">
                <a href="#">Welcome, <%=employee.getUserName()%></a>
            </div>


        </div>


    </div>
    <!------------------------------------------------->

<!-- Form Name -->
<%--<legend>Create Event</legend>--%>
<%--<h4>for <%=event.getEventName()%> event</h4>--%>

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
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collection of nav links and other content for toggling -->
            <div id="navbarCollapse" class="collapse navbar-collapse">
                <ul class="nav nav-tabs">
                    <li><a href="/homepagerecruiter">Home</a></li>
                    <li><a href="#">Profile</a></li>
                    <li  class="active"><a href="/createevent">Create new Event</a></li>
                    <%--<li><a href="#"> <%=employee.getUserName()%></a></li>--%>
                    <li><a href="#" id="logout"> Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="container-fluid">
    <h4 style="color: #1b6d85;"><b>Select Interviewers </b></h4>
<br>
    <h6>Filter By</h6>

    <label class="a-form-label" for="competency">
        <span>Competency</span>
    <select id="competency" name="competency">
        <option value="000">Select</option>
        <option value="Technical">Technical</option>
        <option value="HR">HR</option>
        <option value="MR">MR</option>
    </select>
    </label>
    <label class="a-form-label" for="joblevel">
        <span>Job Level</span>
    <select id="joblevel" name="joblevel">
        <option value="000">Select</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
    </select>
    </label>

    <a class="btn" onclick="clearFilter()">Clear Filters</a>
    <br>
    <br>
    <br>
    <div class="container">
        <div class="row">
            <%--<div class="col-sm-1"><b>Id</b></div>--%>
            <div class="col-sm-1"><b><h5>Username</h5></b></div>
            <div class="col-sm-1"><b><h5>Competency</h5></b></div>
            <div class="col-sm-1"><b><h5>Joblevel</h5></b></div>
            <div class="col-sm-2"><b><h5>Select round</h5></b></div>
            <div class="col-sm-1"><b></b></div>
        </div>
        <br>
    </div>

    <div id="items" class="col-sm-10">
    </div>


</div>
    <%--<br>
    <button id="submit" name="submit" class="btn btn-primary" >Submit</button>
    <br>--%>
    <br><br>

    <div class="a-form-label">
        <label class="col-md-4 control-label" for="submit"></label>
        <div class="col-md-4">
            <button id="submit" name="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>

    <script id="example-template" type="text/x-handlebars-template">
        <%--<h1>{{items[0].name}}</h1>--%>

        {{#each items}}
        <div class="container">
            <div class="row">
                <%--<div class="col-sm-1">{{id}}</div>--%>
                <div class="col-sm-1">{{username}}</div>
                <div class="col-sm-1">{{competency}}</div>
                <div class="col-sm-1">{{joblevel}}</div>
                <div class="col-sm-2">
                    <div class="a-form-label" id="round">
                        <select id="rnd" name="rnd" class="rnd">
                            <option value="0">Select</option>
                            <%Collection<Round> r = event.getRounds();%>
                            <%for(Round rnd : r) {%>
                                <option value="<%=rnd.getRoundId()%>"><%=rnd.getRoundId()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="col-sm-1">
                    <div class="a-form-label" id="select">
                        <input class="checkbox" type="checkbox" name="interviewer" id="{{id}}" value="{{id}}">
                    </div>
                </div>
            </div>
        </div>
        {{/each}}
    </script>

</div>
</body>
</html>



