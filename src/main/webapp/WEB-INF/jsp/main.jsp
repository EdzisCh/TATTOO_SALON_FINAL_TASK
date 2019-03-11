<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Tattoo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="apple-icon.png">
        <link rel="shortcut icon" href="../../static/images/favicon.ico">

        <link rel="stylesheet" href="../../static/vendors/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../static/vendors/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="../../static/vendors/themify-icons/css/themify-icons.css">
        <link rel="stylesheet" href="../../static/vendors/flag-icon-css/css/flag-icon.min.css">
        <link rel="stylesheet" href="../../static/vendors/selectFX/css/cs-skin-elastic.css">
        <link rel="stylesheet" href="../../static/vendors/jqvmap/dist/jqvmap.min.css">

        <link rel="stylesheet" href="../../static/assets/css/style.css">

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
    </head>
    </head>
    <body>
    <!-- Left Panel -->

    <jsp:include page="leftPanel.jsp"/>

    <jsp:include page="header.jsp"/>

    <div class="col-sm-12">
        <div class="alert  alert-success alert-dismissible fade show" role="alert">
            <span class="badge badge-pill badge-success">Success</span> Welcome to our salon, ${pageContext.request.getParameter("login")}.
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>

    <jsp:include page="script.jsp"/>
    </body>
</html>
