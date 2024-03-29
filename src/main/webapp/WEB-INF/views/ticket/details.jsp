<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>

<%@ include file="../include/header.jspf" %>
<%@ include file="../include/navbar.jspf" %>

<body>
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2 class="">Title: ${ticket.title}</h2> <br>
                <p>Category: ${ticket.category.name}
                    <c:choose>
                        <c:when test="${ticket.status == 'open'}">
                            <span class="label label-success label-default-mod">${ticket.status}</span>
                        </c:when>
                        <c:otherwise>
                            <span class="label label-danger">${ticket.status}</span>
                        </c:otherwise>
                    </c:choose>
                </p>
                <p class="label label-success label-default-mod label-xs">Ticket ${ticket.id}</p><span></span>
                <p class="label label-success label-default-mod label-xs">Created by: </p><span> ${ticket.user.username}</span>
                <p class="pull-right">
                    <fmt:parseDate value="${ticket.created}}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
                </p>
            </div>

            <div class="panel-body">


                <div class="ticket-info">
                    <p>${ticket.text}</p>


                    <%@include file="../file/filelist.jsp" %>
                </div>

            </div>
        </div>


        <hr>

        <div class="comments">
            <c:forEach items="${ticket.comment}" var="comment">
                <div class="panel panel-default">

                    <div class="panel panel-heading">
                        <p class="label label-success label-default-mod label-xs">Created by
                        </p> <span> ${comment.user.username}</span>

                        <sec:authorize access="hasRole('ADMIN')">
                            <span class="pull-right">
                                <a href="#"  class="btn btn-default btn-default-mod btn-xs"
                                   data-toggle="modal"
                                   data-target="#deleteModal"
                                   data-comment-id="${comment.id}"
                                   data-ticket-id="${ticket.id}"
                                   title="Delete comment">Delete</a>
                            </span>
                        </sec:authorize>
                        <span class="pull-right">
                                <fmt:parseDate value="${comment.created}}" pattern="yyyy-MM-dd'T'HH:mm"
                                            var="parsedDateTime" type="both"/>
                                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
                        </span>
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
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Potwierdzenie usunięcia</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <p>Czy na pewno usunąć<strong><span id="bookTitle"></span></strong>?</p>
            </div>

            <div class="modal-footer">
                <button id="deleteId" type="button" class="btn btn-default btn-default-mod">Confirm</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    function confirmDeleteComment(tid, cid) {
        if (confirm("Do you want to delete a comment?")) {
            window.location.href = "/ticket/" + tid + "/comment/" + cid + "/delete";
        }
    }

    $(document).ready(function(){
        $('#deleteModal').on('show.bs.modal', function (event) {
            let cid = $(event.relatedTarget).data('comment-id');
            let tid = $(event.relatedTarget).data('ticket-id');
            // $(this).find('.modal-body p #bookTitle').text(bookTitle);
            $('#deleteId').on('click', function () {
                window.location.href = "/ticket/" + tid + "/comment/" + cid + "/delete";
            })
        });
    });
</script>
</html>