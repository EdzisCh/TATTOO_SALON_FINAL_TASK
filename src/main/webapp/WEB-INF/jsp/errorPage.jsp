<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"/>
    <title>Ooops...</title>
</head>
<body>

<jsp:include page="leftPanel.jsp"/>

<div id="right-panel" class="right-panel">
    <jsp:include page="header.jsp"/>
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="alerts">
                <div class="row">
                        <div class="card">
                            <div class="card-header">
                                <strong class="card-title">Ooops...</strong>
                            </div>
                            <div class="card-body">
                                <div class="alert alert-warning" role="alert">
                                    <h4 class="alert-heading">Something happens</h4>
                                    <p>
                                        Request from ${pageContext.errorData.requestURI} is failed
                                        <br/>
                                        Servlet name or type: ${pageContext.errorData.servletName}
                                        <br/>
                                        Status code: ${pageContext.errorData.statusCode}
                                        <br/>
                                        Exception: ${pageContext.errorData.throwable}
                                    </p>
                                    <hr>
                                    <p class="mb-0">Another text information</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- .alerts -->
        </div><!-- .animated -->
    </div><!-- .content -->
<jsp:include page="script.jsp"/>
</body>
</html>
