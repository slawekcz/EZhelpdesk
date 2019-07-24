<%--
  Created by IntelliJ IDEA.
  User: A754366
  Date: 2019-07-24
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<p>${user.id}</p>
<p>${user.firstName}</p>
<p>${user.lastName}</p>
<p>${user.email}</p>
Enabled: ${user.enabled}
<div>
<a href="<c:url value="${pageContext.request.contextPath}/admin/user/block/${user.id}/?action=disable"/>"
   class="label label-danger">Disable </a>
</div>
<div> <br>
<a href="<c:url value="${pageContext.request.contextPath}/admin/user/block/${user.id}/?action=enable"/>"
   class="label label-info">Enable</a>
</div>


</body>
</html>
