<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tattoo</title>
</head>
<body>
    <c:if test="${sessionScope.userLogin == null}">
        <jsp:forward page="../WEB-INF/jsp/login.jsp"/>
    </c:if>
    <c:if test="${sessionScope.userLogin != null}">
        <jsp:forward page="../WEB-INF/jsp/main.jsp"/>
    </c:if>
</body>
</html>
