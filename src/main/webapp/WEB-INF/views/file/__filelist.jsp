<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <div>
        <form:form method="POST" action="/files/list" enctype="multipart/form-data">
            <input type="hidden" name="ticketId" value="${ticket.id}"/>
            <div class="form-inline" >
                <input type="file" name="file" id="file" class="form-control" style="background-color: #feeec4"/>
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
                    <a href="#"  class="btn btn-default btn-default-mod btn-xs"
                       data-toggle="modal"
                       data-target="#deleteFileModal"
                       data-file-id="${file.id}"
                       data-ticket-id="${ticket.id}"
                       title="Delete file">Delete</a>
            </span>
            </div>
        </c:forEach>
    </div>
</div>

<div class="modal fade" id="deleteFileModal" tabindex="-1" role="dialog">
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

<script>
    function confirmDelete(id, tid,  name) {
        if (confirm("Do you want to delete a file \"" + name + "\"?")) {
            window.location.href = "/ticket/" +  tid + "/files/delete/" + id ;
        }
    }

    function confirmDeleteFile(tid, cid) {
        if (confirm("Do you want to delete a comment?")) {
            window.location.href = "/ticket/" + tid + "/comment/" + cid + "/delete";
        }
    }

    $(document).ready(function(){
        $('#deleteFileModal').on('show.bs.modal', function (event) {
            let fid = $(event.relatedTarget).data('file-id');
            let tid = $(event.relatedTarget).data('ticket-id');
            // $(this).find('.modal-body p #bookTitle').text(bookTitle);
            $('#deleteId').on('click', function () {
                window.location.href = "/ticket/" + tid + "/files/delete/" + fid;
            })
        });
    });
</script>
