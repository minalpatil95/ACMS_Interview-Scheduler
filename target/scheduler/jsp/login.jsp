<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html class="a-no-js">
<head>
    <meta charset="utf-8">
    <title dir="ltr">Interview Scheduler Sign IN</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <!-- <link rel="stylesheet" href="https://images-na.ssl-images-amazon.com/images/I/61gbb09bfIL._RC|11Fd9tJOdtL.css,21ULbzscqzL.css,31Q3id-QR0L.css,31QszevPBSL.css_.css#AUIClients/AmazonUI.min" /> -->
    <link rel="stylesheet" href="../css/amazon1.css"/>
    <link rel="stylesheet" href="../css/amazon2.css"/>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/login.js"></script>
    <!-- <link rel="stylesheet" href="https://images-na.ssl-images-amazon.com/images/G/01/AUIClients/CVFAssets-e91ba5c6e67c58c7f9c4c413fa67697feade389e._V2_.css#AUIClients/CVFAssets.secure.min" /> -->
</head>
<body>
<!--Header-->
<div class="row">
    <div class="page-header">
        <h1 style="color: #1b6d85; display: inline-block; text-align: justify;">&emsp;Interview Scheduler</h1>
    </div>
</div>
<div id="alerts"></div>
<div id="a-page">
    <div class="a-section a-padding-medium auth-workflow">
        <div class="a-section a-spacing-base auth-pagelet-container">
            <div id="auth-cookie-warning-message" class="a-box a-alert a-alert-warning">
                <div class="a-box-inner a-alert-container">
                    <h4 class="a-alert-heading">Please Enable Cookies to Continue</h4>
                    <i class="a-icon a-icon-alert"></i>
                    <div class="a-alert-content">
                        <p>
                            <a class="a-link-normal" href="/gp/help/customer/display.html/ref=ap_cookie_error_help?">
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="a-section auth-pagelet-container">

            <div class="a-section auth-pagelet-container">

                <div class="a-section a-spacing-base">
                    <div class="a-section">
                        <form name="signIn" id = "signIn" novalidate action='#'>
                            <div class="a-section">
                                <div class="a-box">
                                    <div class="a-box-inner a-padding-extra-large">
                                        <h1 class="a-spacing-small" style = "text-align: center">
                                            Login
                                        </h1>
                                        <!-- optional subheading element -->
                                        <div class="a-row a-spacing-base">
                                            <label for="ap_email" class="a-form-label">
                                                Email
                                            </label>
                                            <input type="email" maxlength="128" id="ap_email" name="email" tabindex="1"
                                                   class="a-input-text a-span12 auth-autofocus auth-required-field" style="background-color: #d6d8db;">
                                            <br>
                                            <span class="error text-danger" id="emailError" style="background-color: #d6d8db;"></span>
                                            <span class="error text-danger" id="verificationError" style="background-color: #d6d8db;"></span>

                                            <br>
                                            <%--<div class="form-group">
                                                <strong>Password</strong>
                                                <input id="signinPassword" type="password" maxlength="25" class="form-control">
                                            </div>--%>
                                            <label for="ap_password" class="a-form-label">
                                                Password
                                            </label>

                                            <input type="password" maxlength="128" id="ap_password" name="psword"
                                                   tabindex="1"
                                                   class="a-input-text a-span12 auth-autofocus auth-required-field"
                                                   style="background-color: #d6d8db;">
                                            <span class="error text-danger" id="passwordError" style="background-color: #d6d8db;"></span>
                                        </div>
                                        <div class="a-section">
                                    <span id="continue" class="a-button a-button-span12 a-button-primary"><span
                                            class="a-button-inner"><input id="continue" tabindex="5"
                                                                          class="a-button-input" type="submit"
                                                                          aria-labelledby="continue-announce"><span
                                            id="continue-announce" class="a-button-text" aria-hidden="true"
                                            style="background-color:#9b997f;">
                                    <b>Continue</b>
                                    </span></span>
                                    </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="right-2">
            </div>
        </div>
        <div id="auth-external-javascript" class="auth-external-javascript" data-external-javascripts="">
        </div>
        <!-- cache slot rendered -->
    </div>
    <div id='be' style="display:none;visibility:hidden;">
        <form name='ue_backdetect' action="get"><input type="hidden" name='ue_back' value='1'/></form>
    </div>
    <noscript>
        <img height="1" width="1" style='display:none;visibility:hidden;'
             src='//fls-eu.amazon.com/1/batch/1/OP/A21TJRUUN4KGV:262-6062281-6227166:FBET0ZQEF9FTK4P81YVP$uedata=s:%2Fap%2Fuedata%3Fnoscript%26id%3DFBET0ZQEF9FTK4P81YVP:0'
             alt=""/>
    </noscript>
</div>
</body>
</html>