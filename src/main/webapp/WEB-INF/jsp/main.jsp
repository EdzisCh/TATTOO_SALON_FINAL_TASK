<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.chebotar.command.CommandType" %>
<html class="no-js" lang="en">
    <head>

        <jsp:include page="head.jsp"/>
        <title>Tattoo</title>

    </head>
    <body class="bg-dark">

    <jsp:include page="leftPanel.jsp"/>

    <div id="right-panel" class="right-panel">
        <jsp:include page="header.jsp"/>

        <c:if test="${sessionScope.userLogin != null}">
        <div class="col-sm-12">
            <div class="alert  alert-success alert-dismissible fade show" role="alert">
                <span class="badge badge-pill badge-success">Success</span> Welcome to our salon, ${sessionScope.get("userLogin")}!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        </c:if>

        <c:if test="${sessionScope.isAdmin == true}">
            <form name="userList" method="get" action="index">
                <input type="hidden" name="command" value="${CommandType.VIEW_USER_LIST}">
            <div class="content mt-3">
                <div class="animated fadeIn">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">User List</strong>
                                </div>
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Surname</th>
                                           <%-- <th scope="col">Role</th>--%>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <c:forEach items="${sessionScope.userList}" var="user">
                                                <td><c:out value="${user.getEmail()}"/></td>
                                            </c:forEach>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <c:forEach items="${sessionScope.userList}" var="user">
                                                <td><c:out value="${user.getName()}"/></td>
                                            </c:forEach>
                                        </tr>
                                        <tr>
                                            <th scope="row">3</th>
                                            <c:forEach items="${sessionScope.userList}" var="user">
                                                <td><c:out value="${user.getSurname()}"/></td>
                                            </c:forEach>
                                        </tr>
                                        <%--<tr>
                                            <th scope="row">4</th>
                                            <c:forEach items="${sessionScope.userList}" var="user">
                                                <td><c:out value="${user.getEmail()}"/></td>
                                            </c:forEach>
                                        </tr>--%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form>
        </c:if>
    </div>

    <jsp:include page="script.jsp"/>
    </body>
</html>
