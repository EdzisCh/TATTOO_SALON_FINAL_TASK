<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.chebotar.controller.command.CommandType" %>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""><![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8" lang=""><![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9" lang=""><![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">

<head>

    <jsp:include page="head.jsp"/>
    <title>Sign Up</title>

</head>

<body class="bg-dark">


    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    <a href="login.jsp">
                        <img class="align-content" src="../../static/images/logo.png" alt="">
                    </a>
                </div>
                <div class="login-form">
                    <form name="signin" method="post" action="index">
                        <input type="hidden" name="command" value="${CommandType.REGISTER_USER}"/>
                        <div class="form-group">
                            <label>User Login</label>
                            <input type="text" name="login" class="form-control" placeholder="User Login">
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="firstName" class="form-control" placeholder="Name">
                        </div>
                        <div class="form-group">
                            <label>Surname</label>
                            <input type="text" name="secondName" class="form-control" placeholder="Surname">
                        </div>
                        <div class="form-group">
                            <label>Email address</label>
                            <input type="email"  name="email" class="form-control" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="password" class="form-control" placeholder="Password">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Agree the terms and policy
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Register</button>
                    </form>
                    <form name="login" method="get" action="index">
                        <input type="hidden" name="command" value="${CommandType.GET_LOG_IN_PAGE}">
                        <div class="register-link m-t-15 text-center">
                            <p>Already have account ? <button type="submit">Sign in</button></p>
                        </div>
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
