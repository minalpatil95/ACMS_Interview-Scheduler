<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 27/5/18
  Time: 4:15 AM
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
    <script src="../js/url-v2.5.2.js"></script>
    <script src="../js/response.js"></script>
    <%Event event = (Event)request.getAttribute("event"); %>
    <%Collection<Event> pendingEvents = (Collection<Event>) request.getAttribute("pendingEvents");%>
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

    <div class="container-fluid">
        <div class="col-md-6">
            <div class="a-section">
                <div class="a-box">
                    <div class="a-box-inner a-padding-extra-large">

                        <h1 class="a-spacing-small">Event Details</h1>

                        <div class="a-row a-spacing-base">

                            <div><b>Event Name :</b> <%=event.getEventName()%></div>
                            <div><b>Event Date :</b> <%=event.getEventDate()%></div>
                            <div><b>Event type :</b> <%=event.getEventType()%></div>
                            <div><b>Round and Competency :</b><%for(Round r:event.getRounds()) {%>
                                <br><%=r.getRoundId()%>-<%=r.getCompetency()%>
                            <%}%></div>
                            <div><b>Job Level :</b> <%=event.getJobLevel()%></div>
                            <div class="a-form-label" class="row" id="select">
                                <div class="col-sm-2"><input class="radio" type="radio" name="response" id="accept" value="accept"><b>accept</b></div>
                                <div class="col-sm-2"><input class="radio" type="radio" name="response" id="reject" value="reject"><b>reject</b></div>
                            </div>
                        </div>

                        <button id="submit" name="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

