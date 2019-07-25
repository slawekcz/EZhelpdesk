<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-ticket">Categories</i>
            </div>

            <div class="panel-body">
                <c:if test="${empty categories}">
                    <p>No users.</p>
                </c:if>

                <table class="table">

                    <tbody>
                    <c:forEach items="${categories}" var="category">
                        <tr>
                            <td>
                                <a href="<c:url value="${pageContext.request.contextPath}/category/${category.id}"/>">${category.name}</a>
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
