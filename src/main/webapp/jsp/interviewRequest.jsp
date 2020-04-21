<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 27/5/18
  Time: 3:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.minal.scheduler.utils.TokenAuth" %>
<%@ page import="com.minal.scheduler.model.Employee" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.minal.scheduler.model.Event" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>INTERVIEW SCHEDULER</title>

    <meta charset="utf-8">
    <title dir="ltr">Amazon Sign IN</title>
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
    <script src="../js/createEvent.js"></script>
    <% Collection<Event> events = (Collection<Event>) request.getAttribute("events");%>
    <% Collection<Event> pendingEvents = (Collection<Event>) request.getAttribute("pendingEvents");%>
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
    <%--<legend>Interviewer Homepage</legend>--%>

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
                        <li><a href="/homepageinterviewer">Home</a></li>
                        <li><a href="#">Profile</a></li>
                        <li class="active"><a href="/interviewrequest">Interiew Requests (<%=pendingEvents.size()%>)</a></li>
                        <%--<li><a href="#"> <%=employee.getUserName()%></a></li>--%>
                        <li><a href="#" id="logout"> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <div class="container">
        <h4 style="color: #1b6d85;">Pending Requests</h4>
        <hr>
    </div>

    <div class="container-fluid">

        <div class="container">
            <div class="row">
                <%--<div class="col-sm-1"><b>Event Id</b></div>--%>
                <div class="col-sm-1"><b>Event Name</b></div>
                <div class="col-sm-1"><b>Event Date</b></div>
                <div class="col-sm-1"><b>Job Level</b></div>
            </div>

            <%for (Event e: pendingEvents){%>
            <div class="row">
                <div class="item-box-big">
                    <%--<div class="col-sm-1"><%=e.getId()%></div>--%>
                    <div class="col-sm-1"><%=e.getEventName()%></div>
                    <div class="col-sm-1"><%=e.getEventDate()%></div>
                    <div class="col-sm-1"><%=e.getJobLevel()%></div>
                    <%--<div class="col-sm-1"><a href="/event/selectint?id=<%=e.getId()%>">Select Interviewers</a></div>--%>
                    <div class="col-sm-2"><a href="/response?id=<%=e.getId()%>">Event details</a></div>
                </div>
            </div>
            <%}%>
        </div>
    </div>





</div>
</body>
</html>

