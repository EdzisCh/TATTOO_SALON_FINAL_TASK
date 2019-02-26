<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>LogIn</title>
    </head>
    <body>
        <form name="login" method="post" action="controller">
            <input type="hidden" name="command" value="login"/>
            Login:<br/>
            <input type="text" name="login" value=""/>
            <br/>Password:<br/>
            <input type="password" name="password" value=""/>
            <br/>
            ${errorLoginPassMessage}
            <br/>
            ${wrongAction}
            <br/>
            ${nullPage}
            <br/>
            <input type="submit" value="Log in"/>
        </form>
    </body>
</html>
