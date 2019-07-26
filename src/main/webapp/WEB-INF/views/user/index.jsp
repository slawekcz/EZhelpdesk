<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../include/header.jspf"%>
<body>
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-body">
                <c:if test="${empty users}">
                    <p>No users.</p>
                </c:if>

                <table class="table">
                    <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>

                        <th style="text-align:center" colspan="2">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                 <span style="color: #f14444"> ${user.firstName }  ${user.lastName}</span>
                            </td>
                            <td>
                                <a href=<c:url value="${pageContext.request.contextPath}/user/${user.id}"/>>

                                </a>
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
