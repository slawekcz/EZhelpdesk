<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../include/header.jspf" %>
<%@ include file="../include/navbar.jspf" %>

<body>
<div class="row" style="margin-bottom: 20px">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-ticket"> Tickets</i>
            </div>

            <div class="panel-body">
                <c:if test="${empty tickets}">
                    <p>No tickets registered.</p>
                </c:if>

                <table class="table">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Last Updated</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tickets}" var="ticket">
                        <tr>
                            <td>
                                <a href=<c:url value="${pageContext.request.contextPath}/ticket/${ticket.id}"/>>
                                        ${ticket.id } - ${ticket.title}
                                </a>
                            </td>
                            <td>
                                    ${ticket.category.name }
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${ticket.status == 'open'}">
                                        <span class="label label-success">${ticket.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-danger">${ticket.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${ticket.created}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/ticket/${ticket.id}"
                                   class="label label-info">Comment</a>
                                <c:if test="${ticket.status == 'open'}">
                                    <a href="${pageContext.request.contextPath}admin/ticket/close/${ticket.id}"
                                       class="label label-danger">Close</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>