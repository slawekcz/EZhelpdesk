<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: A754366
  Date: 2019-07-23
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../include/header.jspf" %>

<body>
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">Open New Ticket</div>

            <div class="panel-body">
                <div class="alert alert-success">
                    Success
                </div>

                <form:form class="form-horizontal" role="form" method="POST" modelAttribute="ticket">
                    User=${user.id}
                    <input:hidden path="user.id" value="${user.id}"/>

                    <div class="form-group">
                        <form:label path="title" class="col-md-4 control-label">Title</form:label>

                        <div class="col-md-6">
                            <form:input path="title" id="title" type="text" class="form-control" value=""/>
                            <form:errors path="title"/>
                        </div>
                    </div>

                    <div class="form-group has-error">
                        <form:label path="category" for="category" class="col-md-4 control-label">Category</form:label>

                        <div class="col-md-6">
                            <form:select path="category" class="form-control">
                                <form:option value="0">--</form:option>
                                <form:options items="${categories}" itemValue="id" itemLabel="name"></form:options>
                            </form:select>

                            <form:errors path="category"/>
                        </div>
                    </div>

                    <div class="form-group has-error">
                        <label for="priority" class="col-md-4 control-label">Priority</label>

                        <div class="col-md-6">
                            <form:select path="priority" class="form-control" name="priority">
                                <form:option value="">Select Priority</form:option>
                                <form:option value="low">Low</form:option>
                                <form:option value="medium">Medium</form:option>
                                <form:option value="high">High</form:option>
                            </form:select>

                            <form:errors path="priority"/>
                        </div>
                    </div>

                    <div class="form-group has-error">
                        <label for="message" class="col-md-4 control-label">Message</label>

                        <div class="col-md-6">
                            <form:textarea path="text" rows="10" class="form-control" name="message"></form:textarea>

                            <form:errors path="text"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-4">
                            <button type="submit" class="btn btn-primary">
                                <i class="fa fa-btn fa-ticket"></i> Open Ticket
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
