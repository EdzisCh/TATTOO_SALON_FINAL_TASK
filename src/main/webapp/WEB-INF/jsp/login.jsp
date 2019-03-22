<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.chebotar.command.CommandType" %>
<html>
    <head>
        <jsp:include page="head.jsp"/>
        <title>Log In</title>
    </head>
    <body class="bg-dark">

    <c:if test="${sessionScope.incorrectData == true }">
            <div class="alert alert-warning" role="alert">
                Incorrect email or password. Please, try again
            </div>
    </c:if>

    <form name="login" method="post" action="index">
        <input type="hidden" name="command" value="${CommandType.LOG_IN}"/>
        <div class="sufee-login d-flex align-content-center flex-wrap">
            <div class="container">
                <div class="login-content">
                    <div class="login-logo">
                        <a href="#">
                            <img class="align-content" src="../../static/images/logo.png" alt="">
                        </a>
                    </div>
                    <div class="login-form">
                        <form>
                            <div class="form-group">
                                <label>Email address</label>
                                <input type="email" name="email" class="form-control" placeholder="mas@mail.ru">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" pattern="(\w|\d|-){1,35}" name="password" class="form-control" placeholder="1234">
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
                        </form>
                        <form name="register" method="post" action="index">
                            <input type="hidden" name="command" value="${CommandType.GET_REGISTRATION_PAGE}"/>
                            <div class="register-link m-t-15 text-center">
                                <p>Don't have account ? <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30"> Sign Up Here</button></p>
                            </div>
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
