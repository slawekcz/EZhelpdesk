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
    <h2 class="center-text">Create your account</h2>
    <form:form class="form-signin center-block" action="/register" method="post" modelAttribute="user">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:label path="email">Email:</form:label>
            <form:input path="email" class="form-control" name="email"/>
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

        <div class="form-group">
            <form:label path="password">Password</form:label>
            <form:input path="password" type="password" class="form-control"/>
            <form:errors path="password" cssClass="error"></form:errors>
        </div>

        <input type='submit' class="btn btn-default btn-default-mod center-block" value="Create account">
    </form:form>

</body>
</html>

