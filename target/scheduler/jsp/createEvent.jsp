<%@ page import="com.minal.scheduler.utils.TokenAuth" %>
<%@ page import="com.minal.scheduler.model.Employee" %>
<%@ page import="com.minal.scheduler.model.Event" %><%--
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
    <fieldset>

        <form id="ap_register_form" name="createevent" method="post" action='createevent'
              class="ap_ango_default auth-validate-form-moa auth-real-time-validation" novalidate>

        <div class="a-row a-spacing-base">

    <label for="eventName" class="a-form-label">Event Name</label>
    <input type="text" maxlength="128" id="eventName" name="eventName" tabindex="1" placeholder="eventName"
           class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">
    <br><br>
    <label class="a-form-label" for="eventType">Event Type</label>
    <select id="eventType" name="eventType">
        <option value="0">Select</option>
        <%--<option value="1">Hiring</option>
        <option value="2">Onsite</option>--%>
        <option value="Hiring">Hiring</option>
        <option value="Onsite">Onsite</option>
    </select>

    <br><br>


    <div class="a-form-label" id="level">
        <label class="a-form-label" for="jobLevel">Job Level</label>
        <select id="jobLevel" name="jobLevel">
            <option value="Job Level 1">Job Level 1</option>
            <option value="Job Level 2">Job Level 2</option>
            <option value="Job Level 3">Job Level 3</option>
            <option value="Job Level 4">Job Level 4</option>
            <option value="Job Level 5">Job Level 5</option>
            <option value="Job Level 6">Job Level 6</option>
        </select>
    </div>
            <br>

    <div class="a-form-label" id="candidateNumber">
        <label class="a-form-label" for="candidates">No. of candidates to be interviewed</label>
        <input id="candidates" name="candidates" type="number" placeholder="No. of candidates" class="form-control input-md" required="">
    </div>
    <br>

    <div class="a-form-label" id = "numberofrounds">
        <label class="col-md-4 control-label" for="round">No. of rounds to be conducted</label>
        <input id="round" name="round" type="number" placeholder="No. of rounds" class="form-control input-md" required="">
        <div id="competency"></div>
    </div>
    <br>
    <label class="a-form-label" for="eventDate">Event Date</label>
    <input id="eventDate" name="eventDate" type="date" class="form-control input-md" required="">
    <br> <br>

    <div class="a-form-label">
        <label class="col-md-4 control-label" for="submit"></label>
        <div class="col-md-4">
            <button id="submit" name="submit" class="btn btn-primary" onclick="confirmation()">Submit</button>
        </div>
    </div>

</div>
        </form>



        <%Event e = (Event)request.getAttribute("event"); %>
        <% System.out.println(e);%>
        <% int eid = 0; %>
        <% if(e!=null) { eid=e.getId(); System.out.println(eid);}%>
        <% Object o = (Object)eid;%>
<%--
        <div class="a-form-label">
            <label class="col-md-4 control-label" for="next"></label>
            <div class="col-md-4">

                    &lt;%&ndash;<button id="next" name="next" class="btn btn-primary" onclick=" <% if(e!=null) {%>nextPage(<%=e.getId()%>)<%}%>">Select Interviewers</button>&ndash;%&gt;

                        <button id="next" name="next" class="btn btn-primary" onclick="nextPage(<%=eid%>)">Select Interviewers</button>
            </div>
        </div>--%>


    </fieldset>
</div>

<%--<script type="text/javascript">
    function nextPage(o) {
        // jsonData = {
        //     "Id":eventId
        // };
        var id = o.valueOf();
        console.log("clicked");
        console.log(id);
        //window.location.replace("/selectinterviewers?eventId="+Id);
    }
</script>--%>

<script type="text/javascript">
    function confirmation() {
        alert("Event created successfully!!");
    }
</script>


</div>
</body>
</html>


