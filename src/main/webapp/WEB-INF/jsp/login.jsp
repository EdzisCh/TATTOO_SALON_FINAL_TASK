<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.chebotar.controller.command.CommandType" %>
<html>
    <head>
        <jsp:include page="head.jsp"/>
        <title>Log In</title>
    </head>
    <body>
    <%----%>
    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <h2>Invalid login or password!</h2>
            </div>
        </div>
    </div>
    <!---->
    <form name="login" method="post" action="index">
        <input type="hidden" name="command" value="${CommandType.LOG_IN}"/>
        <div class="sufee-login d-flex align-content-center flex-wrap">
            <div class="container">
                <div class="login-content">
                    <div class="login-logo">
                        <a href="login.jsp">
                            <img class="align-content" src="../../static/images/logo.png" alt="">
                        </a>
                    </div>
                    <div class="login-form">
                        <form>
                            <div class="form-group">
                                <label>Email address</label>
                                <input type="email" name="email" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" class="form-control" placeholder="Password">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> Remember Me
                                </label>
                                <label class="pull-right">
                                    <a href="#">Forgotten Password?</a>
                                </label>
                            </div>
                            <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">Sign in</button>
                            <div class="social-login-content">
                                <div class="social-button">
                                    <button type="button" class="btn social facebook btn-flat btn-addon mb-3"><i class="ti-facebook"></i>Sign in with facebook</button>
                                    <button type="button" class="btn social twitter btn-flat btn-addon mt-2"><i class="ti-twitter"></i>Sign in with twitter</button>
                                </div>
                            </div>
                            <form name="register" method="post" action="index">
                                <input type="hidden" name="command" value="${CommandType.GET_LOG_IN_PAGE}"/>
                                <div class="register-link m-t-15 text-center">
                                    <p>Don't have account ? <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30"> Sign Up Here</button></p>
                                </div>
                            </form>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="../../static/vendors/jquery/dist/jquery.min.js"></script>
    <script src="../../static/vendors/popper.js/dist/umd/popper.min.js"></script>
    <script src="../../static/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../../static/assets/js/main.js"></script>

    </body>
</html>
