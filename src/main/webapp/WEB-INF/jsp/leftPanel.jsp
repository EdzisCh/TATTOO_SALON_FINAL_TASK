<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.chebotar.command.CommandType" %>
<html>
<head>
    <jsp:include page="head.jsp"/>
</head>
<body>
<aside id="left-panel" class="left-panel">
    <nav class="navbar navbar-expand-sm navbar-default">

        <div class="navbar-header">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="#"><img src="../../static/images/logo.png"></a>
            <a class="navbar-brand hidden" href="#"><img src="../../static/images/logo2.png"></a>
        </div>

        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <h3 class="menu-title">Menu</h3><!-- /.menu-title -->
                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-glass"></i>Pages</a>
                    <ul class="sub-menu children dropdown-menu">
                        <form name="getCatalog" method="GET" action="index">
                            <input type="hidden" name="command" value="${CommandType.GET_CATALOG}">
                            <button class="btn btn-secondary" type="submit">Catalog</button>
                        <%--<li><i type="submit" class="menu-icon fa fa-sign-in"></i><a href="#">Catalog</a></li>--%>
                        </form>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</aside><!-- /#left-panel -->

<!-- Left Panel -->
</body>
</html>
