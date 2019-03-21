<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="no-js" lang="en">
    <head>

        <jsp:include page="head.jsp"/>
        <title>Tattoo</title>

    </head>
    <body class="bg-dark">

    <jsp:include page="leftPanel.jsp"/>

    <div id="right-panel" class="right-panel">
        <jsp:include page="header.jsp"/>

        <c:if test="${param.size() != 0}">
        <div class="col-sm-12">
            <div class="alert  alert-success alert-dismissible fade show" role="alert">
                <span class="badge badge-pill badge-success">Success</span> Welcome to our salon, ${sessionScope.get("userLogin")}!
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        </c:if>
    </div>

    <jsp:include page="script.jsp"/>
    </body>
</html>
