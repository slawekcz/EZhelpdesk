<%--
  Created by IntelliJ IDEA.
  User: A754366
  Date: 2019-07-25
  Time: 09:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>

<%@ include file="../include/header.jspf" %>
<style>
    .error {
        color: red;
    }
</style>
<body>
<h2 class="center-text">Edit user</h2>
<form:form class="form-signin center-block" action="/register" method="post" modelAttribute="user">
    <form:hidden path="id"/>
    <div class="form-group">
        <form:label path="email">Email:</form:label>
        <form:input path="email" class="form-control" name="email" value=""/>
        <form:errors path="email" cssClass="error"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="username">Username:</form:label>
        <form:input path="username" class="form-control" name="email"/>
        <form:errors path="username" cssClass="error"></form:errors>
    </div>

    <div class="form-group">
        <form:label path="firstName">First name:</form:label>
        <form:input path="firstName" class="form-control"/>
        <form:errors path="firstName" cssClass="error"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="lastName">Last name:</form:label>
        <form:input path="lastName" class="form-control"/>
        <form:errors path="lastName" cssClass="error"></form:errors>
    </div>

    <input type='submit' class="btn btn-info center-block" value="Save">
</form:form>

</body>
</html>


</body>
</html>
