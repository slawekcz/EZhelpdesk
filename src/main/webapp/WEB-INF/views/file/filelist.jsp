<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <div>
        <form:form method="POST" action="/files/list" enctype="multipart/form-data">
            <input type="hidden" name="ticketId" value="${ticket.id}"/>
            <div class="form-inline">
                <input type="file" name="file" id="file" class="form-control"/>
                <button type="submit" class="btn btn-primary btn-xs">upload</button>
            </div>
        </form:form>
    </div>
    <div class="pull-right">
        <c:forEach items="${files}" var="file">
            <div>
                <span><a href="/files/download/${file.id}">${file.fileName}</a></span>
                <span>${file.fileType}</span>
                <span>
                <a href="#" onclick="confirmDelete(${file.id}, '${file.fileName}')"
                   class="btn btn-danger btn-xs">Delete</a>
            </span>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function confirmDelete(id, name) {
        if (confirm("Do you want to delete a file \"" + name + "\"?")) {
            window.location.href = "/files/delete/" + id;
        }
    }
</script>
