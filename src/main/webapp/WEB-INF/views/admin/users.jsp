<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>

<%@ include file="../include/header.jspf"%>
<%@ include file="../include/navbar.jspf"%>


<body>
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-ticket"> Tickets</i>
            </div>

            <div class="panel-body">
                <c:if test="${empty users}">
                    <p>No users.</p>
                </c:if>

                <table class="table">
                    <thead>
                    <tr>
                        <th>User</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                <a href="<c:url value="${pageContext.request.contextPath}/admin/user/${user.id}/edit"/>">
                                    <span style="color: #f14444;">${user.firstName } ${user.lastName}</span></a>
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
