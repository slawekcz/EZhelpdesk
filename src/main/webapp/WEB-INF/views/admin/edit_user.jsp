<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<style>
    .error {
        color: red;
    }
</style>

<%@ include file="../include/header.jspf" %>
<%@ include file="../include/navbar.jspf" %>


<body>
<div class="row form-signin center-block">
    <h2 class="center-text">Edit user</h2>
    <form:form class="_form-signin _center-block" action="/admin/user/edit" method="post" modelAttribute="user">
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

</div>

</body>
</html>

