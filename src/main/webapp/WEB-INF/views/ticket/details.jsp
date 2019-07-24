<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!doctype html>
<html>

<%@ include file="../include/header.jspf" %>
<%@ include file="../include/navbar.jspf" %>

<body>
<div class="row">
    <dixv class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${ticket.id} - ${ticket.title} - Created by ${ticket.user.username}
            </div>

            <div class="panel-body">


                <div class="ticket-info">
                    <p>${ticket.text}</p>
                    <p>Category: ${ticket.category.name}</p>
                    <p>
                        <c:choose>
                            <c:when test="${ticket.status == 'open'}">
                                <span class="label label-success">${ticket.status}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-danger">${ticket.status}</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>Created on: ${ticket.created}</p>
                </div>

            </div>
        </div>


        <hr>

        <div class="comments">
            <c:forEach items="${ticket.comment}" var="comment">
                <div class="panel panel-default">

                    <div class="panel panel-heading">
                            ${comment.user.username}
                        <span class="pull-right">${comment.created}</span>
                    </div>

                    <div class="panel panel-body">
                            ${comment.text}
                    </div>
                </div>
            </c:forEach>
        </div>

        <hr>

        <sec:authorize access="isAuthenticated()">
            <div class="panel panel-default">
                <div class="panel-heading">Add reply</div>

                <div class="panel-body">
                    <div class="comment-form">

                        <form:form action="/ticket/${ticket.id}/comment" method="POST" class="form"
                                   modelAttribute="comment">

                            <form:hidden path="ticket.id" value="${ticket.id}"/>
                            <form:hidden path="user.id" value="${currentUser.user.id}"/>

                            <div class="form-group">
                                <form:textarea path="text" rows="10" class="form-control"></form:textarea>
                            </div>

                            <div class="form-group">

                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </sec:authorize>
    </dix`v>
</div>

</body>
</html>
