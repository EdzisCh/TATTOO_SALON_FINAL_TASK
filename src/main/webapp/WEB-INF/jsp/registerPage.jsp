<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.chebotar.controller.command.CommandType" %>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sufee Admin - HTML5 Admin Template</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="../../static/images/favicon.ico">

    <link rel="stylesheet" href="../../static/vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../static/vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="../../static/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="../../static/vendors/selectFX/css/cs-skin-elastic.css">

    <link rel="stylesheet" href="../../static/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>



</head>

<body class="bg-dark">


    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    <a href="login.jsp">
                        <img class="align-content" src="../../images/logo.png" alt="">
                    </a>
                </div>
                <div class="login-form">
                    <form name="signin" method="post" action="index">
                        <input type="hidden" name="command" value=${CommandType.REGISTER_USER}/>
                        <div class="form-group">
                            <label>User Name</label>
                            <input type="email" class="form-control" placeholder="User Name">
                        </div>
                            <div class="form-group">
                                <label>Email address</label>
                                <input type="email" class="form-control" placeholder="Email">
                        </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control" placeholder="Password">
                        </div>
                                    <div class="checkbox">
                                        <label>
                                <input type="checkbox"> Agree the terms and policy
                            </label>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Register</button>
                                    <div class="social-login-content">
                                        <div class="social-button">
                                            <button type="button" class="btn social facebook btn-flat btn-addon mb-3"><i class="ti-facebook"></i>Register with facebook</button>
                                            <button type="button" class="btn social twitter btn-flat btn-addon mt-2"><i class="ti-twitter"></i>Register with twitter</button>
                                        </div>
                                    </div>
                                    <form name="login" method="get" action="index">
                                        <input type="hidden" name="command" value=${CommandType.GET_LOG_IN_PAGE}>
                                        <div class="register-link m-t-15 text-center">
                                            <p>Already have account ? <button type="submit">Sign in</button></p>
                                            <!--<p>Already have account ? <a href="login.jsp"> Sign in</a></p>-->
                                        </div>
                                    </form>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="../../static/vendors/jquery/dist/jquery.min.js"></script>
    <script src="../../static/vendors/popper.js/dist/umd/popper.min.js"></script>
    <script src="../../static/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../../static/assets/js/main.js"></script>


</body>

</html>
