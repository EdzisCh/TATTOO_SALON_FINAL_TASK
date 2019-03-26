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
        <c:if test="${sessionScope.exception != null}">
            <div class="col-sm-12">
                <div class="alert  alert-danger alert-dismissible fade show" role="alert">
                    <span class="badge badge-pill badge-success">Success</span> Ooops! ${sessionScope.exception}!
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.success == true}">
            <div class="col-sm-12">
                <div class="alert  alert-success alert-dismissible fade show" role="alert">
                    <span class="badge badge-pill badge-success">Success</span> Order is accepted successfully!
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
                            <input type="hidden" name="command" value="${CommandType.GET_ORDER_PAGE}"/>
                            <input type="hidden" name="tattoo" value="${tattoo}"/>
                            <button type="submit" class="btn btn-success">Order It</button>
                        </form>
                    </div>
                </div>
            </div>
            </c:forEach>
        </c:if>

        <c:if test="${sessionScope.makeOrder == true}">
        <form action="index" method="post" enctype="multipart/form-data" class="form-horizontal">
            <input type="hidden" name="command" value="${CommandType.MAKE_ORDER}"/>
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-header">
                        <strong>Order Form</strong>
                    </div>
                    <div class="card-body card-block">

                            <div class="row form-group">
                                <div class="col col-md-3"><label class=" form-control-label">User</label></div>
                                <div class="col-12 col-md-9">
                                    <p class="form-control-static">You sign as ${sessionScope.userLogin}</p>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="email-input" class=" form-control-label">Email Input</label></div>
                                <div class="col-12 col-md-9"><input type="email" id="email-input" name="email-input" placeholder="Enter Email" class="form-control"><small class="help-block form-text">Please enter your email</small></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="password-input" class=" form-control-label">Password</label></div>
                                <div class="col-12 col-md-9"><input type="password" id="password-input" name="password-input" placeholder="Password" class="form-control"><small class="help-block form-text">Please enter a complex password</small></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="disabled-input" class=" form-control-label">Disabled Input</label></div>
                                <div class="col-12 col-md-9"><input type="text" id="disabled-input" name="disabled-input" placeholder="Disabled" disabled="" class="form-control"></div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="textarea-input" class=" form-control-label">Add some text</label></div>
                                <div class="col-12 col-md-9"><textarea name="textarea-input" id="textarea-input" rows="9" placeholder="Content..." class="form-control"></textarea></div>
                            </div>
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btn-sm">
                                    <i class="fa fa-dot-circle-o"></i> Submit
                                </button>
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

ё