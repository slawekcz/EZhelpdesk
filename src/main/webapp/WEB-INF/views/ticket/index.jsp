<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <th>Id</th>
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
                                Nr ${ticket.id }
                            </td>
                            <td>
                                <a href=<c:url value="${pageContext.request.contextPath}/ticket/${ticket.id}"/>>
                                       <span style="color: #f14444">${ticket.title}</span>
                                </a>
                            </td>
                            <td>
                                    ${ticket.category.name}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${ticket.status == 'open'}">
                                        <span class="label label-default label-default-mod">${ticket.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-danger">${ticket.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <fmt:parseDate value="${ticket.created}}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />

                            </td>

                            <td>
                                <a href="${pageContext.request.contextPath}/ticket/${ticket.id}"
                                   class="label label-default label-default-mod">Comment</a>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <c:if test="${ticket.status == 'open'}">
                                        <a href="${pageContext.request.contextPath}/ticket/${ticket.id}/close"
                                           class="label label-danger">Close</a>
                                    </c:if>
                                </sec:authorize>
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
