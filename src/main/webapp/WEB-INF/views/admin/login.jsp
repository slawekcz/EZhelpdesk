<%--
  Created by IntelliJ IDEA.
  User: A754366
  Date: 2019-07-24
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../include/header.jspf" %>

<head>
    <title>Login</title>
</head>
<body>
<h2 class="center-text" >Sign in</h2>
<form method="post" class="form-signin center-block">
    <div class="form-group">
        <div><label> User Name : <input type="text" name="username" class="form-control" required/> </label></div>
    </div>

    <div class="form-group">
        <div><label> Password: <input type="password" name="password" class="form-control" required/> </label></div>
    </div>

    <div>
        <input type="submit" value="Sign In" class="btn btn-default btn-default-mod "/>
        <a href="/register"><input type="button" value="Register" class="btn btn-default btn-default-mod"/></a>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
