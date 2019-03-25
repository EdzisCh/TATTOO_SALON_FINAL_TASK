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
                <span class="badge badge-pill badge-success">Success</span> Welcome to our salon, ${sessionScope.userLogin}!
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
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">User List   </strong>
                                    <button class="btn btn-secondary" type="submit">View</button>
                                </div>
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Surname</th>
                                            <th scope="col">Login</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="user" items="${sessionScope.userList}">
                                            <tr>
                                                <th scope="row"><c:out value="${user.getId()}"/></th>
                                                <td><c:out value="${user.getEmail()}"/></td>
                                                <td><c:out value="${user.getFirstName()}"/></td>
                                                <td><c:out value="${user.getLastName()}"/></td>
                                                <td><c:out value="${user.getLogin()}"/></td>
                                            </tr>
                                        </c:forEach>
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

        <c:if test="${sessionScope.viewCatalog == true}">
            <c:forEach var="tattoo" items="${sessionScope.tattooList}">
            <div class="col-md-4">
                <div class="card">
                    <img class="card-img-top" src="${tattoo.getPhoto()}" alt="Tattoo">
                    <div class="card-body">
                        <h4 class="card-title mb-3">${tattoo.getPrice()}</h4>
                        <p class="card-text">${tattoo.getDescription()}</p>
                        <form name="makeOrder" method="post" action="index">
                            <input type="hidden" name="command" value="${CommandType.MAKE_ORDER}">
                            <button type="submit" class="btn btn-success">Order It</button>
                        </form>
                    </div>
                </div>
            </div>
            </c:forEach>
        </c:if>
    </div>

    <jsp:include page="script.jsp"/>
    </body>
</html>

